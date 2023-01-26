package com.tolganacar.weatherforecast.view.threehourlyweather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tolganacar.weatherforecast.databinding.ThreeHourlyWeatherRowBinding
import com.tolganacar.weatherforecast.view.threehourlyweather.uimodel.ThreeHourlyWeatherUIModel

class ThreeHourlyAdapter(
    private val threeHourlyWeatherList: ArrayList<ThreeHourlyWeatherUIModel>
) : RecyclerView.Adapter<ThreeHourlyAdapter.ThreeHourlyViewHolder>() {

    class ThreeHourlyViewHolder(val binding: ThreeHourlyWeatherRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThreeHourlyViewHolder {
        val binding =
            ThreeHourlyWeatherRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ThreeHourlyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ThreeHourlyViewHolder, position: Int) {
        holder.binding.threeHourlyWeather = threeHourlyWeatherList[position]
    }

    override fun getItemCount(): Int {
        return threeHourlyWeatherList.size
    }
}