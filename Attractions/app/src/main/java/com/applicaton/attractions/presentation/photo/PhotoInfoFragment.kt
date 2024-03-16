package com.applicaton.attractions.presentation.photo

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.applicaton.attractions.databinding.FragmentPhotoInfoBinding
import com.applicaton.attractions.presentation.gallery.extractDateTime
import com.bumptech.glide.Glide

private const val ARG_PARAM = "param"

class PhotoInfoFragment : Fragment() {

    private var _binding: FragmentPhotoInfoBinding? = null
    private val binding get() = _binding!!

    private var param: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            param = it?.getString(ARG_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            Glide
                .with(photoImage.context)
                .load(Uri.parse(param))
                .into(photoImage)
        }

        binding.textData.text = param?.let { Uri.parse(param).extractDateTime() }

        binding.buttonBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

}