package com.tolganacar.weatherforecast.view.threehourlyweather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tolganacar.weatherforecast.data.model.threehourlyweather.ThreeHourlyWeatherResponseModel
import com.tolganacar.weatherforecast.databinding.ThreeHourlyWeatherRowBinding

class ThreeHourlyWeatherAdapter(
    private val threeHourlyWeatherList: ArrayList<ThreeHourlyUI>
) : RecyclerView.Adapter<ThreeHourlyWeatherAdapter.ThreeHourlyViewHolder>() {

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

    fun updateThreeHourlyWeatherList(newThreeHourlyWeatherList: List<ThreeHourlyUI>) {
        threeHourlyWeatherList.clear()
        threeHourlyWeatherList.addAll(newThreeHourlyWeatherList)
        notifyDataSetChanged()
    }
}