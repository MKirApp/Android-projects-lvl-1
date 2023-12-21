package com.example.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.timer.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val KEY = "Key"
private const val KEY2 = "Key2"

class MainActivity : AppCompatActivity() {
    private var count = 0
    private var countRotate: Int? = null
    private var isTimerActive: Boolean = true
    private val scope = CoroutineScope(Dispatchers.Main)
    private var job: Job? = null
    private var isActiveRotate: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        countRotate = savedInstanceState?.getInt(KEY, count)
        isActiveRotate = savedInstanceState?.getBoolean(KEY2, isTimerActive)
        if (isActiveRotate == false) {
            count = countRotate as Int
            timer(binding)
        }
        binding.slider.addOnChangeListener { _, value, _ ->
            binding.textView2.text = value.toInt().toString()
            binding.progressBar.max = value.toInt()
            binding.progressBar.progress = value.toInt()
        }
        binding.button.setOnClickListener {
            count = binding.slider.value.toInt()
            timer(binding)
        }
    }

    private fun timer(binding: ActivityMainBinding) {
        if (isTimerActive) {
            startTimer(binding)
        } else {
            stopTimer(binding)
        }
    }

    private fun startTimer(binding: ActivityMainBinding) {
        isTimerActive = !isTimerActive
        binding.button.text = getString(R.string.button_stop)
        binding.slider.isEnabled = false
        job = scope.launch {
            while (count > 0) {
                count--
                binding.textView2.text = count.toString()
                binding.progressBar.progress = count
                delay(1000)
            }
            stopTimer(binding)
        }
    }

    private fun stopTimer(binding: ActivityMainBinding) {
        isTimerActive = !isTimerActive
        binding.progressBar.progress = binding.slider.value.toInt()
        binding.slider.isEnabled = true
        binding.button.text = getString(R.string.button_start)
        binding.textView2.text = binding.slider.value.toInt().toString()
        job?.cancel()
        Toast.makeText(
            this,
            "Timer Task Finished",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY, count)
        outState.putBoolean(KEY2, isTimerActive)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }
}