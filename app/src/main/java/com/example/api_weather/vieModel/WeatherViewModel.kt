package com.example.api_weather.vieModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.api_weather.model.DayItemWeatherModel

class WeatherViewModel:ViewModel() {
    private val mainLivedata = MutableLiveData<DayItemWeatherModel>()
    private val listLivedata = MutableLiveData<List<DayItemWeatherModel>>()
}