package com.example.api_weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.api_weather.databinding.ActivityMainBinding
import com.example.api_weather.fragments.MainFragment

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
}