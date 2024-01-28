package com.applicaton.photosfrommars.presentation

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.applicaton.photosfrommars.App
import com.applicaton.photosfrommars.R
import com.applicaton.photosfrommars.databinding.FragmentFirstBinding

import com.applicaton.photosfrommars.presentation.models.PhotoModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

private const val ARG_PARAM = "param"

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val bundle = Bundle()

    private val viewModel: MainViewModel by activityViewModels {
        (requireContext().applicationContext as App).appComponent.mainViewModelFactory()
    }

    private val pagedAdapter = PhotoPagedListAdapter { photoModel ->
        onItemClick(photoModel)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = pagedAdapter.withLoadStateFooter(CustomLoadStateAdapter())

        binding.swipeRefresh.setOnRefreshListener {
            pagedAdapter.refresh()
        }

        pagedAdapter.loadStateFlow.onEach { loadStates ->
            if (loadStates.refresh is LoadState.Error || loadStates.append is LoadState.Error) {
                Toast.makeText(context, "Возникли проблемы с интернетом", Toast.LENGTH_SHORT).show()
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.state.onEach {
            binding.recyclerView.layoutManager?.onRestoreInstanceState(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        pagedAdapter.loadStateFlow.onEach {
            binding.swipeRefresh.isRefreshing = it.refresh == LoadState.Loading
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.photos.onEach {
            pagedAdapter.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }

    fun onItemClick(item: PhotoModel) {
        bundle.putString(ARG_PARAM, item.img_src)
        binding.recyclerView.layoutManager?.onSaveInstanceState()
            ?.let { viewModel.setStateRecycler(it) }
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}