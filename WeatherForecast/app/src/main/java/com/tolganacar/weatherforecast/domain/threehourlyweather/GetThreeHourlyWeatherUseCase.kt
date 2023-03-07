package com.tolganacar.weatherforecast.domain.threehourlyweather

import com.tolganacar.weatherforecast.base.BaseUseCase
import com.tolganacar.weatherforecast.data.model.currentweather.CurrentWeatherRequest
import com.tolganacar.weatherforecast.data.model.threehourlyweather.getTemperatureText
import com.tolganacar.weatherforecast.data.repository.WeatherRepository
import com.tolganacar.weatherforecast.view.threehourlyweather.ThreeHourlyUI
import com.tolganacar.weatherforecast.view.threehourlyweather.ThreeHourlyWeatherUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetThreeHourlyWeatherUseCase @Inject constructor(
    private val repo: WeatherRepository
) : BaseUseCase<GetThreeHourlyWeatherUseCase.Params, ThreeHourlyWeatherUIModel>() {

    data class Params(
        val cityName: String
    )

    override suspend fun execute(params: Params): Flow<ThreeHourlyWeatherUIModel> =
        repo.getThreeHourlyWeather(CurrentWeatherRequest(params.cityName)).map {
            ThreeHourlyWeatherUIModel(
                hourlyList = it.list.map { listElements ->
                    ThreeHourlyUI(
                        hour = listElements.dt_txt.substring(10,16),
                        temperature = listElements.getTemperatureText(),
                        icon = "http://openweathermap.org/img/w/" + listElements.weather[0].icon + ".png"
                    )
                }
            )
        }
    }