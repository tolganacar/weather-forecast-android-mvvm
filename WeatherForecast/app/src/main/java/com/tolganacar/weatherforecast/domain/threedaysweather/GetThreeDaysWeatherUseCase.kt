package com.tolganacar.weatherforecast.domain.threedaysweather

import com.tolganacar.weatherforecast.base.BaseUseCase
import com.tolganacar.weatherforecast.data.model.threedaysweather.*
import com.tolganacar.weatherforecast.data.repository.WeatherApiRepository
import com.tolganacar.weatherforecast.view.threedaysweather.ForecastDayUI
import com.tolganacar.weatherforecast.view.threedaysweather.ThreeDaysWeatherUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetThreeDaysWeatherUseCase @Inject constructor(
    private val repo: WeatherApiRepository
) : BaseUseCase<GetThreeDaysWeatherUseCase.Params, ThreeDaysWeatherUIModel>() {

    data class Params(
        val cityName: String,
        val days: Int = 10
    )

    override suspend fun execute(params: Params): Flow<ThreeDaysWeatherUIModel> =
        repo.getThreeDaysWeather(ThreeDaysWeatherRequest(params.cityName, params.days)).map { threeDaysWeather ->
                ThreeDaysWeatherUIModel(
                    threeDaysWeatherList = threeDaysWeather.forecast?.forecastday?.map {
                        ForecastDayUI(
                            dateText = it.getDateText(),
                            imageUrl = it.getImageUrl(),
                            minTemperatureText = it.getMinTemperatureText(),
                            maxTemperatureText = it.getMaxTemperatureText()
                        )
                    } ?: listOf()
                )
            }
}
