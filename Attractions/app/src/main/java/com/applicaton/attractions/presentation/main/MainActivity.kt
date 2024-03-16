package com.applicaton.attractions.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.lifecycleScope
import com.applicaton.attractions.R
import com.applicaton.attractions.databinding.ActivityMainBinding
import com.applicaton.attractions.presentation.maps.MapsFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("Tag", "onCreate MainActivity")
        if (savedInstanceState == null) {
            Log.d("Tag","транзакция")
            supportFragmentManager.commit {
                replace<MainFragment>(R.id.placeHolder)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Tag", "onDestroy MainActivity")
        _binding = null
    }
}