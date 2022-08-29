package com.example.api_weather.fragments

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import com.example.api_weather.AndroidUtils
import com.example.api_weather.adapters.VpAdapter
import com.example.api_weather.databinding.FragmentMainBinding
import com.example.api_weather.viewModel.WeatherViewModel
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso


class MainFragment : Fragment() {

    private val listFragments = listOf(HoursFragment(), DaysFragment())
    private val tabList = listOf("HOURS", "DAYS")

    private lateinit var binding: FragmentMainBinding
    private lateinit var pLauncher: ActivityResultLauncher<String>
    private val viewModel: WeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
        viewModel.weatherService.requestWeatherData(view.context, "Donetsk")
        viewModel.mainLivedata.value = viewModel.weatherService.emptyDay
        init()
        cardObserv()
        with(binding) {
            var searchFlag = false
            binding.imageButtonSync.setOnClickListener {
                viewModel.weatherService.requestWeatherData(view.context, "Sydney")
            }

            binding.imageButtonSearch.setOnClickListener() {
                if (!searchFlag) {
                    editTextSearch.text = null
                    editTextSearch.visibility = View.VISIBLE
                    AndroidUtils.showKeyboard(it)
                    editTextSearch.requestFocus()
                    searchFlag = true
                } else{
                    if (editTextSearch.text != null){
                        searchCity(view.context , editTextSearch.text.toString())
                    }
                    editTextSearch.visibility = View.GONE
                    AndroidUtils.hideKeyboard(it)
                    searchFlag = false
                }
            }
        }

    }

    private fun searchCity( context: Context,city:String ){
        viewModel.weatherService.requestWeatherData(context , city)
    }

    private fun init() = with(binding) {
        val vPAdapter = VpAdapter(activity as FragmentActivity, listFragments)
        viewPager.adapter = vPAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabList[position]
        }.attach()

    }

    private fun cardObserv() {
        with(binding) {
            viewModel.mainLivedata.observe(viewLifecycleOwner) {
                textViewMainCardDate.text = it.date
                textViewCity.text = it.city
                textViewTempToday.text = it.currentTemp
                textViewConditionToday.text = it.condition
                textViewMaxMinTemp.text = "${it.maxTemp}°C / ${it.minTemp}°C"
                Picasso.get().load("https:${it.imageUrl}").into(imageViewConditionIcon)

            }
        }
    }

    private fun permissionListener() {
        pLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            Toast.makeText(activity, "permission $it", Toast.LENGTH_LONG).show()
        }
    }


    private fun checkPermission() {
        if (!isPermissionsGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
            permissionListener()
            pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            MainFragment()
    }
}