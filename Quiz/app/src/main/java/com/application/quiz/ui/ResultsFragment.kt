package com.application.quiz.ui

import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.application.quiz.R
import com.application.quiz.databinding.FragmentResultsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ResultsFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentResultsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            param1 = it?.getString(ARG_PARAM1)
            param2 = it?.getString(ARG_PARAM2)
        }
        val inflater = TransitionInflater.from(requireContext())
        exitTransition  = inflater.inflateTransition(R.transition.slide_right)
        enterTransition = inflater.inflateTransition(R.transition.fade)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultsBinding.inflate(inflater)
        ObjectAnimator.ofFloat(binding.buttonStartAgain, View.TRANSLATION_Z, 0f, 1.5f).apply {
            duration = 2000
            interpolator = AccelerateDecelerateInterpolator()
            repeatCount = 3
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        (AnimatorInflater.loadAnimator(requireContext(), R.animator.custom_animation_text) as ObjectAnimator).apply {
            target = binding.textResults
            start()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!param1.isNullOrEmpty()) {
            val firstText = param1?.substringBefore(".").plus(".")
            binding.answer1.text = firstText
            val text2 = param1?.substringAfter(".")
            val secondText = text2?.substringBefore(".").plus(".")
            binding.answer2.text = secondText
            val thirdText = param1?.substringAfterLast(". ")
            binding.answer3.text = thirdText
        }

        binding.buttonStartAgain.setOnClickListener {
            findNavController().navigate(R.id.action_resultsFragment_to_surveyFragment)
        }


    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SecondFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ResultsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

    }
}