package com.tolganacar.weatherforecast.domain.threehourlyweather

import com.tolganacar.weatherforecast.base.BaseUseCase
import com.tolganacar.weatherforecast.data.model.currentweather.CurrentWeatherRequest
import com.tolganacar.weatherforecast.data.model.threehourlyweather.ThreeHourlyWeatherResponseModel
import com.tolganacar.weatherforecast.data.model.threehourlyweather.getHourText
import com.tolganacar.weatherforecast.data.model.threehourlyweather.getImageUrl
import com.tolganacar.weatherforecast.data.model.threehourlyweather.getTemperatureText
import com.tolganacar.weatherforecast.data.repository.OpenWeatherMapRepository
import com.tolganacar.weatherforecast.view.threehourlyweather.ThreeHourlyUI
import com.tolganacar.weatherforecast.view.threehourlyweather.ThreeHourlyWeatherUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetThreeHourlyWeatherUseCase @Inject constructor(
    private val repo: OpenWeatherMapRepository
) : BaseUseCase<GetThreeHourlyWeatherUseCase.Params, ThreeHourlyWeatherUIModel>() {

    data class Params(
        val cityName: String
    )

    override suspend fun execute(params: Params): Flow<ThreeHourlyWeatherUIModel> =
        repo.getThreeHourlyWeather(CurrentWeatherRequest(params.cityName)).map { threeHourly ->
                ThreeHourlyWeatherUIModel(
                    hourlyWeatherList = threeHourly.list?.map {
                        ThreeHourlyUI(
                            hourText = it.getHourText(),
                            temperatureText = it.getTemperatureText(),
                            iconUrl = it.getImageUrl()
                        )
                    } ?: listOf()
                )
            }
        }
