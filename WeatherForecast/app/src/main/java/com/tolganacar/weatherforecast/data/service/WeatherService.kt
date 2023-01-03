package com.tolganacar.weatherforecast.data.service

import com.tolganacar.weatherforecast.data.model.currentweather.CurrentWeatherResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET(CURRENT_WEATHER)
    suspend fun getCurrentCityWeather(@Query(CITY_NAME) cityName: String): CurrentWeatherResponseModel

    companion object {
        const val CITY_NAME = "q"
        const val CURRENT_WEATHER = "data/2.5/weather?&units=metric"
    }

}