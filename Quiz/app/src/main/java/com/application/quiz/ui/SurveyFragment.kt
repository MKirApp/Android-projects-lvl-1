package com.application.quiz.ui

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.application.quiz.CustomRadioGroup
import com.application.quiz.R
import com.application.quiz.databinding.FragmentSurveyBinding
import com.application.quiz.quiz.QuizStorage
import java.util.Locale


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SurveyFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentSurveyBinding? = null
    private val binding get() = _binding!!
    private var questions =
        if (Locale.getDefault().language == "ru") QuizStorage.getQuiz(QuizStorage.Locale.Ru)
        else QuizStorage.getQuiz(QuizStorage.Locale.En)
    private var listAnswer = MutableList<Int>(3) { -1 }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            param1 = it?.getString(ARG_PARAM1)
            param2 = it?.getString(ARG_PARAM2)
        }

        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
        enterTransition = inflater.inflateTransition(R.transition.fade)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSurveyBinding.inflate(inflater)
        binding.surveyFragment.startAnimation(AlphaAnimation(0f, 1f).apply { duration = 2000 })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val radioGroups = arrayOf(R.id.radioGroup1, R.id.radioGroup2, R.id.radioGroup3)
        for (i in radioGroups.indices) {
            val textView = binding.root.findViewById<CustomRadioGroup>(radioGroups[i])
            textView.binding.textViewQuestion.text = questions.questions[i].question
        }
        questions.questions.forEachIndexed { i, it ->
            it.answers.forEachIndexed { index, _ ->
                val radioButton =
                    binding.root.findViewById<CustomRadioGroup>(radioGroups[i]).binding.radioGroup[index] as RadioButton
                radioButton.text = questions.questions[i].answers[index]
            }
        }

        for (i in radioGroups.indices) {
            val radioGroup = binding.root.findViewById<CustomRadioGroup>(radioGroups[i])
            radioGroup.binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.radioButton1 -> listAnswer[i] = 0
                    R.id.radioButton2 -> listAnswer[i] = 1
                    R.id.radioButton3 -> listAnswer[i] = 2
                    R.id.radioButton4 -> listAnswer[i] = 3
                }
                Log.d("radioGroup1", "$listAnswer")
            }
        }
        binding.buttonSend.setOnClickListener {
            if (listAnswer.all { it != -1 }) {
                val bundle = Bundle().apply {
                    putString("param1", QuizStorage.answer(questions, listAnswer))
                }
                findNavController().navigate(
                    R.id.action_surveyFragment_to_resultsFragment,
                    bundle
                )
            } else Toast.makeText(
                requireContext(),
                getString(R.string.toast_text),
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_surveyFragment_to_mainFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        listAnswer.clear()
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

    private fun replaceList(list: MutableList<Int>, element: Int) {
        if (list.size > element - 1) {
            list[element - 1] = list[list.lastIndex]
            if (list.size > element) list.removeAt(list.lastIndex)
        }
    }
}