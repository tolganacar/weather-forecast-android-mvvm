package com.tolganacar.weatherforecast.view.currentweather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolganacar.weatherforecast.domain.currentweather.GetCurrentWeatherUseCase
import com.tolganacar.weatherforecast.domain.tendayweather.GetThreeDaysWeatherUseCase
import com.tolganacar.weatherforecast.domain.threehourlyweather.GetThreeHourlyWeatherUseCase
import com.tolganacar.weatherforecast.view.threedaysweather.ForecastDayUI
import com.tolganacar.weatherforecast.view.threehourlyweather.ThreeHourlyUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val currentWeatherUseCase: GetCurrentWeatherUseCase,
    private val threeHourlyWeatherUseCase: GetThreeHourlyWeatherUseCase,
    private val threeDaysWeatherUseCase: GetThreeDaysWeatherUseCase
) : ViewModel() {

    val currentWeatherUIModel = MutableLiveData<CurrentWeatherUIModel>()
    val threeHourlyWeatherUIModel = MutableLiveData<List<ThreeHourlyUI>>()
    val threeDaysWeatherUIModel = MutableLiveData<List<ForecastDayUI>>()

    val shouldShowErrorMessage = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()

    fun getAllWeatherInformationFromAPI(cityName: String){
        getCurrentWeatherFromAPI(cityName)
        getThreeHourlyWeatherFromAPI(cityName)
        getThreeDaysWeatherFromAPI(cityName)
    }

    fun getCurrentWeatherFromAPI(cityName: String) = viewModelScope.launch {
        currentWeatherUseCase.execute(GetCurrentWeatherUseCase.Params(cityName))
            .onStart { isLoading.value = true }
            .onCompletion { isLoading.value = false }
            .catch {
                shouldShowErrorMessage.value = true
            }
            .collect {
                currentWeatherUIModel.value = it
            }
    }

    fun getThreeHourlyWeatherFromAPI(cityName: String) = viewModelScope.launch {
        threeHourlyWeatherUseCase.execute(GetThreeHourlyWeatherUseCase.Params(cityName))
            .onStart { isLoading.value = true }
            .onCompletion { isLoading.value = false }
            .catch {
                shouldShowErrorMessage.value = true
            }
            .collect {
                threeHourlyWeatherUIModel.value = it.hourlyList
            }
    }

    fun getThreeDaysWeatherFromAPI(cityName: String) = viewModelScope.launch {
        threeDaysWeatherUseCase.execute(GetThreeDaysWeatherUseCase.Params(cityName,3))
            .onStart { isLoading.value = true }
            .onCompletion { isLoading.value = false }
            .catch {
                shouldShowErrorMessage.value = true
            }
            .collect {
                threeDaysWeatherUIModel.value = it.weatherList
            }
    }
}