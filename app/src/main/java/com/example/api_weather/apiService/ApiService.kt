package com.example.api_weather.apiService

import android.content.Context
import com.example.api_weather.model.WeatherModel
import org.json.JSONObject

interface ApiService {
    fun parseWeather(result: String)
    fun parseCurrentData(mainJsonObject: JSONObject , weatherItem:WeatherModel)
    fun parseDays(mainJsonObject: JSONObject):List<WeatherModel>
    fun requestWeatherData(context: Context,city: String)

}