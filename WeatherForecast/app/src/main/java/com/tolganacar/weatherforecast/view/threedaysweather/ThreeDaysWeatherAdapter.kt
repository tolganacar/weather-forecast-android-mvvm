package com.tolganacar.weatherforecast.view.threedaysweather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tolganacar.weatherforecast.databinding.ThreeDaysWeatherRowBinding

class ThreeDaysWeatherAdapter(
    private val threeDaysWeatherList: ArrayList<ForecastDayUI>
) : RecyclerView.Adapter<ThreeDaysWeatherAdapter.ThreeDaysWeatherViewHolder>() {

    class ThreeDaysWeatherViewHolder(val binding: ThreeDaysWeatherRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThreeDaysWeatherViewHolder {
        val binding =
            ThreeDaysWeatherRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ThreeDaysWeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ThreeDaysWeatherViewHolder, position: Int) {
        holder.binding.threeDaysWeather = threeDaysWeatherList[position]
    }

    override fun getItemCount(): Int {
        return threeDaysWeatherList.size
    }

    fun updateTenDayWeatherList(newThreeDaysWeatherList: List<ForecastDayUI>) {
        threeDaysWeatherList.clear()
        threeDaysWeatherList.addAll(newThreeDaysWeatherList)
        notifyDataSetChanged()
    }
}