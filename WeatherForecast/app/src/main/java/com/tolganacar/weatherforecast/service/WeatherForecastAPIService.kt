package com.tolganacar.weatherforecast.service

import com.tolganacar.weatherforecast.model.CurrentWeatherResponseModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WeatherForecastAPIService {

    private val BASE_URL = "https://api.openweathermap.org/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(WeatherForecastAPI::class.java)

    fun getIstanbulWeather(): Single<CurrentWeatherResponseModel> {
        return api.getIstanbulWeather()
    }
}