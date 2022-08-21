package com.example.api_weather.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.api_weather.R
import com.example.api_weather.databinding.ItemLayoutBinding
import com.example.api_weather.model.DayItemWeatherModel

class RcViewAdapter :
    ListAdapter<DayItemWeatherModel, RcViewAdapter.WeatherViewHolder>(Comparator()) {

    class WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemLayoutBinding.bind(view)
        fun bind(item: DayItemWeatherModel) {
            with(binding) {
                textViewDate.text = item.date
                textViewCondition.text = item.condition
                textViewTemp.text = item.currentTemp
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.item_layout , parent , false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}


class Comparator() : DiffUtil.ItemCallback<DayItemWeatherModel>() {
    override fun areItemsTheSame(
        oldItem: DayItemWeatherModel,
        newItem: DayItemWeatherModel
    ): Boolean {
        return oldItem == newItem
    }


    override fun areContentsTheSame(
        oldItem: DayItemWeatherModel,
        newItem: DayItemWeatherModel
    ): Boolean {
        return oldItem == newItem
    }

}