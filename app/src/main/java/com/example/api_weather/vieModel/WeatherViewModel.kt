package com.example.api_weather.vieModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.api_weather.model.WeatherModel

class WeatherViewModel:ViewModel() {
    private val mainLivedata = MutableLiveData<WeatherModel>()
    private val listLivedata = MutableLiveData<List<WeatherModel>>()
}