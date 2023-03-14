package com.tolganacar.weatherforecast.data.service

import com.tolganacar.weatherforecast.data.model.threedaysweather.ThreeDaysWeatherResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET(OpenWeatherMapService.THREE_DAYS_WEATHER)
    suspend fun getThreeDaysWeather(
        @Query(OpenWeatherMapService.CITY_NAME) cityName: String,
        @Query(OpenWeatherMapService.DAYS) days: Int
    ): ThreeDaysWeatherResponseModel
}