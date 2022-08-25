package com.example.api_weather.viewModel

import android.content.Context
import android.provider.SyncStateContract.Helpers.update
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.api_weather.KEY
import com.example.api_weather.apiService.ApiService
import com.example.api_weather.apiService.ApiServiceImpl
import com.example.api_weather.model.WeatherModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject


class WeatherViewModel() : ViewModel() {

    val mainLivedata = MutableLiveData<WeatherModel>()
    val listLivedata = MutableLiveData<List<WeatherModel>>()
    val weatherService = ApiServiceImpl(mainLivedata)

}