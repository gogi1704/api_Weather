package com.example.api_weather.apiService

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.api_weather.KEY
import com.example.api_weather.model.WeatherModel
import org.json.JSONObject

class ApiServiceImpl(private val liveData: MutableLiveData<WeatherModel>) : ApiService {

    var emptyDay = WeatherModel(
        city = "",
        date = "",
        condition = "",
        imageUrl = "",
        currentTemp = "",
        maxTemp = "",
        minTemp = "",
        hours = ""
    )
        set(value) {
            field = value
            liveData.value = field
        }


    override fun parseWeather(result: String) {
        val jsonResult = JSONObject(result)
        val listDays = parseDays(jsonResult)
        parseCurrentData(jsonResult, listDays[0])
        Log.d(
            "My log", "ParseWeather $emptyDay"
        )

    }


    override fun parseCurrentData(
        mainJsonObject: JSONObject,
        weatherItem: WeatherModel
    ): WeatherModel {
        emptyDay = emptyDay.copy(
            city = mainJsonObject.getJSONObject("location").getString("name"),
            date = mainJsonObject.getJSONObject("current").getString("last_updated"),
            condition = mainJsonObject.getJSONObject("current").getJSONObject("condition")
                .getString("text"),
            imageUrl = mainJsonObject.getJSONObject("current").getJSONObject("condition")
                .getString("icon"),
            currentTemp = mainJsonObject.getJSONObject("current").getString("temp_c"),
            maxTemp = weatherItem.maxTemp,
            minTemp = weatherItem.minTemp,
            hours = weatherItem.hours
        )
        return emptyDay
    }

    override fun parseDays(mainJsonObject: JSONObject): List<WeatherModel> {
        val listDays = ArrayList<WeatherModel>()
        val daysArray = mainJsonObject.getJSONObject("forecast")
            .getJSONArray("forecastday")

        for (i in 0 until daysArray.length()) {
            val forecastDay = daysArray[i] as JSONObject
            val dayItem = WeatherModel(
                city = mainJsonObject.getJSONObject("location").getString("name"),
                date = forecastDay.getString("date"),
                condition = forecastDay.getJSONObject("day").getJSONObject("condition")
                    .getString("text"),
                imageUrl = forecastDay.getJSONObject("day").getJSONObject("condition")
                    .getString("icon"),
                currentTemp = mainJsonObject.getJSONObject("current").getString("temp_c"),
                maxTemp = forecastDay.getJSONObject("day").getString("maxtemp_c"),
                minTemp = forecastDay.getJSONObject("day").getString("mintemp_c"),
                hours = forecastDay.getJSONArray("hour").toString()
            )
            listDays.add(dayItem)

        }
        return listDays
    }

    override fun requestWeatherData(context: Context, city: String) {
        val url =
            "https://api.weatherapi.com/v1/forecast.json?key=$KEY&q=$city&days=3&aqi=no&alerts=no"
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET,
            url,
            { result ->
                parseWeather(result)
            },
            { error ->
                Log.d(
                    "My log", "error" +
                            " $error"
                )

            }
        )
        queue.add(request)
    }

    fun requestDaysData(){}
}