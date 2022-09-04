package com.example.api_weather.viewModel

import android.Manifest
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.LocationRequest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.api_weather.apiService.ApiServiceImpl
import com.example.api_weather.model.WeatherModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource


class WeatherViewModel() : ViewModel() {


    val mainLivedata = MutableLiveData<WeatherModel>()
    val listLivedata = MutableLiveData<List<WeatherModel>>()
    val weatherService = ApiServiceImpl(mainLivedata, listLivedata)




    @RequiresApi(Build.VERSION_CODES.S)
    fun getLocation(context: Context, fusedLocationClient :FusedLocationProviderClient) {
        val token = CancellationTokenSource()
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient.getCurrentLocation(LocationRequest.QUALITY_HIGH_ACCURACY, token.token)
            .addOnCompleteListener {
                weatherService.requestWeatherData(
                    context,
                    "${it.result.latitude},${it.result.longitude}"
                )
            }
    }
    fun searchCity(context: Context, city: String) {
        weatherService.requestWeatherData(context, city)
    }
}