package com.tolganacar.weatherforecast.data.service

import com.tolganacar.weatherforecast.data.model.currentweather.CurrentWeatherResponseModel
import com.tolganacar.weatherforecast.data.model.currentweather.ThreeHourlyWeatherResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET(CURRENT_WEATHER)
    suspend fun getCurrentCityWeather(@Query(CITY_NAME) cityName: String): CurrentWeatherResponseModel

    @GET(THREE_HOURLY_WEATHER)
    suspend fun getThreeHourlyWeather(@Query(CITY_NAME) cityName: String): ThreeHourlyWeatherResponseModel

    companion object {
        const val CITY_NAME = "q"
        const val CURRENT_WEATHER = "data/2.5/weather?&units=metric"
        const val THREE_HOURLY_WEATHER = "data/2.5/forecast?&units=metric"
    }

}