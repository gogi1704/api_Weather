package com.example.api_weather.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.api_weather.adapters.RcViewDaysAdapter
import com.example.api_weather.adapters.RcViewHoursAdapter
import com.example.api_weather.databinding.FragmentDaysBinding
import com.example.api_weather.viewModel.WeatherViewModel

class DaysFragment : Fragment() {
    private lateinit var adapter: RcViewDaysAdapter
    private lateinit var binding: FragmentDaysBinding
    private val viewModel: WeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        adapter = RcViewDaysAdapter()
        binding = FragmentDaysBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        viewModel.listLivedata.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }


    private fun initRecyclerView() {
        with(binding) {
            rV.adapter = adapter
            adapter.submitList(viewModel.listLivedata.value)

        }
    }

    private fun parseDays() {}


    companion object {

        @JvmStatic
        fun newInstance() = DaysFragment()
    }
}