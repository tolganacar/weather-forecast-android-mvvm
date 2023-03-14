package com.tolganacar.weatherforecast.data.repository

import com.tolganacar.weatherforecast.data.model.currentweather.CurrentWeatherRequest
import com.tolganacar.weatherforecast.data.model.currentweather.CurrentWeatherResponseModel
import com.tolganacar.weatherforecast.data.model.threehourlyweather.ThreeHourlyWeatherResponseModel
import kotlinx.coroutines.flow.Flow

interface OpenWeatherMapRepository {

    suspend fun getCurrentWeather(req: CurrentWeatherRequest): Flow<CurrentWeatherResponseModel>

    suspend fun getThreeHourlyWeather(req: CurrentWeatherRequest): Flow<ThreeHourlyWeatherResponseModel>
}