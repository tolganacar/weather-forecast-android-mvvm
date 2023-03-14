package com.tolganacar.weatherforecast.data.repository

import com.tolganacar.weatherforecast.data.model.threedaysweather.ThreeDaysWeatherRequest
import com.tolganacar.weatherforecast.data.model.threedaysweather.ThreeDaysWeatherResponseModel
import com.tolganacar.weatherforecast.data.service.WeatherApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.lang.Exception
import javax.inject.Inject

class WeatherApiRepositoryImpl @Inject constructor(
    private val service: WeatherApiService
) : WeatherApiRepository {

    override suspend fun getThreeDaysWeather(req: ThreeDaysWeatherRequest): Flow<ThreeDaysWeatherResponseModel> {
        return try {
            flowOf(service.getThreeDaysWeather(req.cityName,req.days))
        } catch (e: Exception) {
            throw e
        }
    }
}