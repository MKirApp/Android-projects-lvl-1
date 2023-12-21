package com.application.quiz.ui

import android.icu.util.Calendar
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.application.quiz.R
import com.application.quiz.databinding.FragmentMainBinding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MainFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val dateFormat = SimpleDateFormat("dd.MM.yy")
    private val calendar = Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.slide_left)
        enterTransition = inflater.inflateTransition(R.transition.slide_left)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonContinue.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_surveyFragment)
        }
        binding.buttonDatePicker.setOnClickListener {
            val constraints = CalendarConstraints.Builder()
                .setOpenAt(calendar.timeInMillis)
                .build()
            MaterialDatePicker.Builder.datePicker()
                .setTitleText(resources.getString(R.string.enter_data))
                .setCalendarConstraints(constraints)
                .build().apply {
                    addOnPositiveButtonClickListener {
                        calendar.timeInMillis = it
                        Snackbar.make(
                            binding.buttonDatePicker,
                            "${resources.getString(R.string.selected_data)} ${
                                dateFormat.format(
                                    calendar.time
                                )
                            }",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
                .show(childFragmentManager, "DatePicker")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}