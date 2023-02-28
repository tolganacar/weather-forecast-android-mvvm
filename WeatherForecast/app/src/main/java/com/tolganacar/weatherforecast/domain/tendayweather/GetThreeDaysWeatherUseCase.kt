package com.tolganacar.weatherforecast.domain.tendayweather

import com.tolganacar.weatherforecast.base.BaseUseCase
import com.tolganacar.weatherforecast.data.model.threedaysweather.*
import com.tolganacar.weatherforecast.data.repository.WeatherThreeDaysRepository
import com.tolganacar.weatherforecast.view.threedaysweather.ForecastDayUI
import com.tolganacar.weatherforecast.view.threedaysweather.ThreeDaysWeatherUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetThreeDaysWeatherUseCase @Inject constructor(
    private val repo: WeatherThreeDaysRepository
) : BaseUseCase<GetThreeDaysWeatherUseCase.Params, ThreeDaysWeatherUIModel>() {

    data class Params(
        val cityName: String,
        val days: Int=10
    )

    override suspend fun execute(params: Params): Flow<ThreeDaysWeatherUIModel> =
        repo.getThreeDaysWeather(ThreeDaysWeatherRequest(params.cityName, params.days)).map {
            ThreeDaysWeatherUIModel(
                weatherList = it.forecast.forecastday.map { forecast ->
                    ForecastDayUI(
                        date = forecast.date,
                        image = "https://" + forecast.day.condition.icon,
                        minTemperature = forecast.day.mintemp_c.toString() + "\u00B0",
                        maxTemperature = forecast.day.maxtemp_c.toString() + "\u00B0"
                    )
                }
            )
        }
}