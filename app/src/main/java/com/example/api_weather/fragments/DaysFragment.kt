package com.example.api_weather.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.api_weather.adapters.RcViewAdapter
import com.example.api_weather.databinding.FragmentDaysBinding
import com.example.api_weather.model.WeatherModel

class DaysFragment : Fragment() {

    private lateinit var binding: FragmentDaysBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDaysBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }



    companion object {

        @JvmStatic
        fun newInstance() = DaysFragment()
    }
}