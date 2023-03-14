package com.tolganacar.weatherforecast.domain.currentweather

import com.tolganacar.weatherforecast.base.BaseUseCase
import com.tolganacar.weatherforecast.data.model.currentweather.*
import com.tolganacar.weatherforecast.data.repository.OpenWeatherMapRepository
import com.tolganacar.weatherforecast.view.currentweather.CurrentWeatherUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val repo: OpenWeatherMapRepository
) : BaseUseCase<GetCurrentWeatherUseCase.Params, CurrentWeatherUIModel>() {

    data class Params(
        val cityName: String
    )

    override suspend fun execute(params: Params): Flow<CurrentWeatherUIModel> =
        repo.getCurrentWeather(CurrentWeatherRequest(params.cityName)).map {
            with(it) {
                CurrentWeatherUIModel(
                    name = name ?: params.cityName,
                    temperature = getTemperatureText(),
                    weatherStatus = getWeatherStatus(),
                    weatherMain = getWeatherMain(),
                    minTemperature = getMinTemperatureText(),
                    maxTemperature = getMaxTemperatureText(),
                    currentWeatherThemeUI = getCurrentWeatherTheme()
                )
            }

        }
}