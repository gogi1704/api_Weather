package com.example.api_weather.model

data class DayItemWeatherModel(
    val city : String,
    val date : String,
    val condition : String,
    val imageUrl : String,
    val currentTemp :String,
    val maxTemp : String,
    val minTemp : String,
    val hours:String
)
