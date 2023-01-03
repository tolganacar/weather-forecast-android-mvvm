package com.tolganacar.weatherforecast.data.repository

import com.tolganacar.weatherforecast.data.model.currentweather.CurrentWeatherRequest
import com.tolganacar.weatherforecast.data.service.WeatherService
import com.tolganacar.weatherforecast.domain.repository.WeatherRepository
import com.tolganacar.weatherforecast.data.model.currentweather.CurrentWeatherResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.lang.Exception
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val service: WeatherService
) : WeatherRepository {

    override suspend fun getCurrentWeather(req: CurrentWeatherRequest): Flow<CurrentWeatherResponseModel> {
        return try {
            flowOf(service.getCurrentCityWeather(req.cityName))
        } catch (e: Exception) {
            throw e
        }
    }

}