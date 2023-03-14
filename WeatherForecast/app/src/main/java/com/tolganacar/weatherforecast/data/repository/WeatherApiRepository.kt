package com.tolganacar.weatherforecast.data.repository

import com.tolganacar.weatherforecast.data.model.threedaysweather.ThreeDaysWeatherRequest
import com.tolganacar.weatherforecast.data.model.threedaysweather.ThreeDaysWeatherResponseModel
import kotlinx.coroutines.flow.Flow

interface WeatherApiRepository {

    suspend fun getThreeDaysWeather(req: ThreeDaysWeatherRequest): Flow<ThreeDaysWeatherResponseModel>
}