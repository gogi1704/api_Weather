package com.example.api_weather.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.api_weather.adapters.RcViewAdapter
import com.example.api_weather.databinding.FragmentDaysBinding
import com.example.api_weather.model.WeatherModel
import com.example.api_weather.viewModel.WeatherViewModel


val list = listOf<WeatherModel>(
    WeatherModel("", "", "", "", "", "", "", ""),
    WeatherModel("", "", "", "", "", "", "", ""),
    WeatherModel("","","","","","","","", )
)

class DaysFragment : Fragment() {
    private lateinit var adapter: RcViewAdapter
    private lateinit var binding: FragmentDaysBinding
    val viewModel:WeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        adapter = RcViewAdapter()
        binding = FragmentDaysBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

    }


    private fun initRecyclerView() {
        with(binding) {
            rV.adapter = adapter
            adapter.submitList(list)

        }
    }


    companion object {

        @JvmStatic
        fun newInstance() = DaysFragment()
    }
}