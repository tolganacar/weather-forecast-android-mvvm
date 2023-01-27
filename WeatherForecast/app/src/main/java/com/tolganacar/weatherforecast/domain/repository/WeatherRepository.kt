package com.tolganacar.weatherforecast.domain.repository

import com.tolganacar.weatherforecast.data.model.currentweather.CurrentWeatherRequest
import com.tolganacar.weatherforecast.data.model.currentweather.CurrentWeatherResponseModel
import com.tolganacar.weatherforecast.data.model.currentweather.ThreeHourlyWeatherResponseModel
import com.tolganacar.weatherforecast.data.model.tendayweather.TenDayWeatherResponseModel
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getCurrentWeather(req: CurrentWeatherRequest): Flow<CurrentWeatherResponseModel>

    suspend fun getThreeHourlyWeather(req: CurrentWeatherRequest): Flow<ThreeHourlyWeatherResponseModel>

    suspend fun getTenDayWeather(req: CurrentWeatherRequest): Flow<TenDayWeatherResponseModel>

}