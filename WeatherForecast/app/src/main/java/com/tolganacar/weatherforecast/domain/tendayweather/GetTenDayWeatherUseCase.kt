package com.tolganacar.weatherforecast.domain.tendayweather

import com.tolganacar.weatherforecast.base.BaseUseCase
import com.tolganacar.weatherforecast.data.model.currentweather.CurrentWeatherRequest
import com.tolganacar.weatherforecast.data.model.tendayweather.getDateText
import com.tolganacar.weatherforecast.data.model.tendayweather.getImage
import com.tolganacar.weatherforecast.data.model.tendayweather.getMaxTemperatureText
import com.tolganacar.weatherforecast.data.model.tendayweather.getMinTemperatureText
import com.tolganacar.weatherforecast.domain.repository.WeatherRepository
import com.tolganacar.weatherforecast.domain.threehourlyweather.GetThreeHourlyWeatherUseCase
import com.tolganacar.weatherforecast.view.tendayweather.TenDayWeatherUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTenDayWeatherUseCase @Inject constructor(
    private val repo: WeatherRepository
) : BaseUseCase<GetTenDayWeatherUseCase.Params, TenDayWeatherUIModel>(){

    data class Params(
        val cityName: String
    )

    override suspend fun execute(params: Params): Flow<TenDayWeatherUIModel> =
        repo.getTenDayWeather(CurrentWeatherRequest(params.cityName)).map {
            TenDayWeatherUIModel(
                date = it.getDateText(),
                image = it.getImage(),
                minTemperature = it.getMinTemperatureText(),
                maxTemperature = it.getMaxTemperatureText()
            )
        }

}