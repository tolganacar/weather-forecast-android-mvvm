package com.tolganacar.weatherforecast.service

import com.tolganacar.weatherforecast.model.CurrentWeatherResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherForecastAPI {

    @GET("data/2.5/weather?&units=metric&q=istanbul&appid=d226094cc9cf2c082beaaee4d673c381")
    fun getIstanbulWeather(): Single<CurrentWeatherResponseModel>

    @GET("data/2.5/weather?&units=metric&lat={lat}&lon={lon}&appid=d226094cc9cf2c082beaaee4d673c381")
    fun getCurrentWeather(
        @Path("lat") lat: Double,
        @Path("lon") lon: Double
    ): Single<List<CurrentWeatherResponseModel>>

    @GET("data/2.5/weather?&units=metric&q={city name}&appid=d226094cc9cf2c082beaaee4d673c381")
    fun getCurrentCityWeather(
        @Path("city name") name: String
    ): Single<List<CurrentWeatherResponseModel>>
}