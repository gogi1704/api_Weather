package com.example.api_weather.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.api_weather.R
import com.example.api_weather.adapters.RcViewAdapter
import com.example.api_weather.adapters.VpAdapter
import com.example.api_weather.databinding.FragmentDaysBinding
import com.example.api_weather.databinding.FragmentMainBinding
import com.example.api_weather.model.DayItemWeatherModel

class DaysFragment : Fragment() {
    private val list = listOf(
        DayItemWeatherModel(
            city = "Donetsk",
            date = "22/03/22",
            condition = "sunny",
            imageUrl = "",
            currentTemp = "28",
            maxTemp = "29",
            minTemp = "19",
            hours = ""
        ),
        DayItemWeatherModel(
            city = "Lugansk",
            date = "22/04/22",
            condition = "sunny",
            imageUrl = "",
            currentTemp = "21",
            maxTemp = "29",
            minTemp = "19",
            hours = ""
        ),
        DayItemWeatherModel(
            city = "Kiev",
            date = "22/05/22",
            condition = "sunny",
            imageUrl = "",
            currentTemp = "17",
            maxTemp = "29",
            minTemp = "19",
            hours = ""
        )
    )
    private lateinit var adapter: RcViewAdapter
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
        initRcView()

    }

    private fun initRcView() = with(binding) {
        val adapter = RcViewAdapter()
        rV.adapter = adapter
        adapter.submitList(list)
    }

    companion object {

        @JvmStatic
        fun newInstance() = DaysFragment()
    }
}