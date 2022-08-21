package com.example.api_weather.vieModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WeatherViewModel:ViewModel() {
    private val mainLivedata = MutableLiveData<String>()
    private val listLivedata = MutableLiveData<String>()
}