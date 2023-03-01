package com.tolganacar.weatherforecast.data.service

import com.tolganacar.weatherforecast.data.model.threedaysweather.ThreeDaysWeatherResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherThreeDaysService {

    //http://api.weatherapi.com/v1/forecast.json?key=abc1e2bd0ee7486484a110827232601&q=istanbul&days=10
    @GET(WeatherService.THREE_DAYS_WEATHER)
    suspend fun getThreeDaysWeather(
        @Query(WeatherService.CITY_NAME) cityName: String,
        @Query(WeatherService.DAYS) days: Int
    ): ThreeDaysWeatherResponseModel
}