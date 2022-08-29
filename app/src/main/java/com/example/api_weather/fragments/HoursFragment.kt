package com.example.api_weather.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.api_weather.adapters.RcViewHoursAdapter
import com.example.api_weather.databinding.FragmentHoursBinding
import com.example.api_weather.model.WeatherModel
import com.example.api_weather.viewModel.WeatherViewModel
import org.json.JSONArray
import org.json.JSONObject

class HoursFragment : Fragment() {
    private lateinit var adapter: RcViewHoursAdapter
    private lateinit var binding: FragmentHoursBinding
    private val viewModel: WeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHoursBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()


        viewModel.mainLivedata.observe(viewLifecycleOwner) {
            adapter.submitList(parseHours(it))
        }
    }


    private fun initRcView() = with(binding) {
        adapter = RcViewHoursAdapter()
        rV.adapter = adapter
    }

    private fun parseHours(dayItem: WeatherModel): ArrayList<WeatherModel> {
        val listParseHours = ArrayList<WeatherModel>()
        if (dayItem.city != "Searching") {
            val jsonArrayHours = JSONArray(dayItem.hours)
            for (i in 0 until jsonArrayHours.length() step 2) {
                val parsDay = WeatherModel(
                    dayItem.city,
                    date = (jsonArrayHours[i] as JSONObject).getString("time"),
                    condition = (jsonArrayHours[i] as JSONObject).getJSONObject("condition")
                        .getString("text"),
                    imageUrl = (jsonArrayHours[i] as JSONObject).getJSONObject("condition")
                        .getString("icon"),
                    currentTemp = (jsonArrayHours[i] as JSONObject).getString("temp_c"),
                    maxTemp = "",
                    minTemp = "",
                    hours = "",
                )
                listParseHours.add(parsDay)
            }
            return listParseHours
        }else
        return ArrayList()

    }

    companion object {

        @JvmStatic
        fun newInstance() = HoursFragment()
    }
}