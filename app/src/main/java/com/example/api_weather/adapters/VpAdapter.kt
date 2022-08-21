package com.example.api_weather.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class VpAdapter(fragmentAct: FragmentActivity, private val listFragments: List<Fragment>) :
    FragmentStateAdapter(fragmentAct) {
    override fun getItemCount(): Int {
        return listFragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return listFragments[position]
    }
}