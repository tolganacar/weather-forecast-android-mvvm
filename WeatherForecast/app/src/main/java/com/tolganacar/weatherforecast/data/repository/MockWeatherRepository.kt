package com.tolganacar.weatherforecast.data.repository

import com.tolganacar.weatherforecast.data.model.currentweather.CurrentWeatherRequest
import com.tolganacar.weatherforecast.domain.repository.WeatherRepository
import com.tolganacar.weatherforecast.data.model.currentweather.CurrentWeatherResponseModel
import com.tolganacar.weatherforecast.data.model.currentweather.ThreeHourlyWeatherResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MockWeatherRepository : WeatherRepository {
    override suspend fun getCurrentWeather(req: CurrentWeatherRequest): Flow<CurrentWeatherResponseModel> {
        return flowOf(CurrentWeatherResponseModel())
    }

    override suspend fun getThreeHourlyWeather(req: CurrentWeatherRequest): Flow<ThreeHourlyWeatherResponseModel> {
        return flowOf(ThreeHourlyWeatherResponseModel())
    }
}