package com.tolganacar.weatherforecast.domain.threehourlyweather

import com.tolganacar.weatherforecast.base.BaseUseCase
import com.tolganacar.weatherforecast.data.model.currentweather.CurrentWeatherRequest
import com.tolganacar.weatherforecast.data.model.currentweather.getIcon
import com.tolganacar.weatherforecast.data.model.currentweather.getTemperatureText
import com.tolganacar.weatherforecast.data.model.currentweather.getTime
import com.tolganacar.weatherforecast.domain.repository.WeatherRepository
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
                temperature = it.getTemperatureText(),
                hour = it.getTime(),
                icon = it.getIcon()
            )
        }
    }