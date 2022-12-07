package com.tolganacar.weatherforecast.service

import com.tolganacar.weatherforecast.model.CurrentWeatherResponseModel
import io.reactivex.Single
import retrofit2.http.GET

interface WeatherForecastAPI {

    @GET("data/2.5/weather?lat={lat}&lon={lon}&appid={API key}")
    fun getCurrentWeather(): Single<List<CurrentWeatherResponseModel>>
}