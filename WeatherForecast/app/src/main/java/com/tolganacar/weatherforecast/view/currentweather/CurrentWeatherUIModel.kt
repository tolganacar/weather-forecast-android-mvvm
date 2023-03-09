package com.tolganacar.weatherforecast.view.currentweather

data class CurrentWeatherUIModel(
    val name: String,
    val temperature: String,
    val weatherStatus: String,
    val weatherMain: String,
    val maxTemperature: String,
    val minTemperature: String
)
