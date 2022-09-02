package com.example.api_weather

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.fragment.app.activityViewModels
import com.example.api_weather.databinding.ActivityMainBinding
import com.example.api_weather.fragments.MainFragment
import com.example.api_weather.model.WeatherModel
import com.example.api_weather.viewModel.WeatherViewModel
import org.json.JSONObject

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, MainFragment.newInstance())
            .commit()

    }

    override fun onDestroy() {
        super.onDestroy()

    }



}