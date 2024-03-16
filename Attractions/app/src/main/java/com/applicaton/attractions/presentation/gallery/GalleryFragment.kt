package com.applicaton.attractions.presentation.gallery

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.allViews
import androidx.core.view.forEach
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.applicaton.attractions.R
import com.applicaton.attractions.app.App
import com.applicaton.attractions.databinding.FragmentGalleryBinding
import com.applicaton.attractions.databinding.PhotoItemBinding
import com.applicaton.attractions.presentation.gallery.models.PhotoGallery
import com.applicaton.attractions.presentation.photo.PhotoFragment
import com.applicaton.attractions.presentation.photo.PhotoInfoFragment
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.io.File

private const val ARG_PARAM = "param"

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    private val bundle = Bundle()

    private val viewModel: GalleryViewModel by activityViewModels {
        (requireContext().applicationContext as App).getAppComponent().galleryViewModelFactory()
    }

    private val galleryListAdapter = GalleryListAdapter(
        onClick = { photoGallery ->
            onItemClick(photoGallery)
        },
        onDelete = { photoGallery ->
            onDeleteClick(photoGallery)
        },

        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = galleryListAdapter
        binding.recyclerView.addItemDecoration(object : ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                val space = 10
                if (parent.getChildLayoutPosition(view) % 2 == 0) {
                    outRect.top = space * 2
                    outRect.left = space * 5
                    outRect.right = space * 3
                    outRect.bottom = space * 2
                } else {
                    outRect.top = space * 2
                    outRect.bottom = space * 2
                    outRect.left = space * 3
                }
            }
        }
        )

        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.allPhotos.collect {
                    galleryListAdapter.submitList(it)
                }
            }
        }

        binding.buttonPhoto.setOnClickListener {
            parentFragmentManager.commit {
                replace<PhotoFragment>(R.id.placeHolder)
                addToBackStack(GalleryFragment::class.java.simpleName)
            }
        }
    }

    private fun onItemClick(photoGallery: PhotoGallery) {
        bundle.putString(ARG_PARAM, photoGallery.uri)
        parentFragmentManager.commit {
            replace<PhotoInfoFragment>(R.id.placeHolder, null, bundle)
            addToBackStack(GalleryFragment::class.java.simpleName)
        }
    }

    private fun onDeleteClick(photoGallery: PhotoGallery) {
        photoGallery.isSelected = !photoGallery.isSelected
        viewModel.onDeleteButton(photoGallery)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}