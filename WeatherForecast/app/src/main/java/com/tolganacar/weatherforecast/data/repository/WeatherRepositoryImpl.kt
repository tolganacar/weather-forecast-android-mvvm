package com.tolganacar.weatherforecast.data.repository

import com.tolganacar.weatherforecast.data.model.currentweather.CurrentWeatherRequest
import com.tolganacar.weatherforecast.data.service.WeatherService
import com.tolganacar.weatherforecast.data.model.currentweather.CurrentWeatherResponseModel
import com.tolganacar.weatherforecast.data.model.threehourlyweather.ThreeHourlyWeatherResponseModel
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

    override suspend fun getThreeHourlyWeather(req: CurrentWeatherRequest): Flow<ThreeHourlyWeatherResponseModel> {
        return try {
            flowOf(service.getThreeHourlyWeather(req.cityName))
        } catch (e: Exception) {
            throw e
        }
    }


}