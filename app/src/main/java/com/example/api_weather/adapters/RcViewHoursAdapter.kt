package com.example.api_weather.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.api_weather.R
import com.example.api_weather.databinding.ItemLayoutBinding
import com.example.api_weather.model.WeatherModel
import com.squareup.picasso.Picasso

class RcViewHoursAdapter :
    ListAdapter<WeatherModel, RcViewHoursAdapter.WeatherViewHolder>(ComparatorHours) {

    class WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemLayoutBinding.bind(view)
        fun bind(item: WeatherModel) {
            with(binding) {

                textViewDate.text = item.date
                textViewCondition.text = item.condition
                textViewTemp.text = "${item.currentTemp}°C"
                Picasso.get().load("https:${item.imageUrl}").into(imageViewCondition)
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


object ComparatorHours : DiffUtil.ItemCallback<WeatherModel>() {
    override fun areItemsTheSame(
        oldItem: WeatherModel,
        newItem: WeatherModel
    ): Boolean {
        return oldItem == newItem
    }


    override fun areContentsTheSame(
        oldItem: WeatherModel,
        newItem: WeatherModel
    ): Boolean {
        return oldItem == newItem
    }

}