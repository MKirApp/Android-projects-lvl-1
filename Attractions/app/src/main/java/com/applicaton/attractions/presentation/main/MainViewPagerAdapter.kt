package com.applicaton.attractions.presentation.main

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.applicaton.attractions.presentation.gallery.GalleryFragment
import com.applicaton.attractions.presentation.maps.MapsFragment

class MainViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        Log.d("Tag", "MainFragment create()")
        return when (position) {
            0 -> GalleryFragment()
            1 -> MapsFragment()
            else -> throw IllegalStateException("Invalid position")
        }
    }



}