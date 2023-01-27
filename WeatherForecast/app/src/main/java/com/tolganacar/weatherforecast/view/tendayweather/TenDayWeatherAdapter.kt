package com.tolganacar.weatherforecast.view.tendayweather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tolganacar.weatherforecast.databinding.TenDayWeatherRowBinding

class TenDayWeatherAdapter(
    private val tenDayWeatherList: ArrayList<TenDayWeatherUIModel>
) : RecyclerView.Adapter<TenDayWeatherAdapter.TenDayWeatherViewHolder>() {

    class TenDayWeatherViewHolder(val binding: TenDayWeatherRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TenDayWeatherViewHolder {
        val binding =
            TenDayWeatherRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TenDayWeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TenDayWeatherViewHolder, position: Int) {
        holder.binding.tenDayWeather = tenDayWeatherList[position]
    }

    override fun getItemCount(): Int {
        return tenDayWeatherList.size
    }
}