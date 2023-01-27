package com.tolganacar.weatherforecast.view.currentweather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolganacar.weatherforecast.domain.currentweather.GetCurrentWeatherUseCase
import com.tolganacar.weatherforecast.domain.tendayweather.GetTenDayWeatherUseCase
import com.tolganacar.weatherforecast.domain.threehourlyweather.GetThreeHourlyWeatherUseCase
import com.tolganacar.weatherforecast.view.tendayweather.TenDayWeatherUIModel
import com.tolganacar.weatherforecast.view.threehourlyweather.ThreeHourlyWeatherUIModel
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
    private val tenDayWeatherUseCase: GetTenDayWeatherUseCase
) : ViewModel() {

    val currentWeatherUIModel = MutableLiveData<CurrentWeatherUIModel>()
    val threeHourlyWeatherUIModel = MutableLiveData<ThreeHourlyWeatherUIModel>()
    val tenDayWeatherUIModel = MutableLiveData<TenDayWeatherUIModel>()

    val shouldShowErrorMessage = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()

    fun getCurrentWeatherFromAPI() = viewModelScope.launch {
        currentWeatherUseCase.execute(GetCurrentWeatherUseCase.Params("İstanbul"))
            .onStart { isLoading.value = true }
            .onCompletion { isLoading.value = false }
            .catch {
                shouldShowErrorMessage.value = true
            }
            .collect {
                currentWeatherUIModel.value = it
            }
    }

    fun getThreeHourlyWeatherFromAPI() = viewModelScope.launch {
        threeHourlyWeatherUseCase.execute(GetThreeHourlyWeatherUseCase.Params("İstanbul"))
            .onStart { isLoading.value = true }
            .onCompletion { isLoading.value = false }
            .catch {
                shouldShowErrorMessage.value = true
            }
            .collect {
                threeHourlyWeatherUIModel.value = it
            }
    }

    fun getTenDayWeatherFromAPI() = viewModelScope.launch {
        tenDayWeatherUseCase.execute(GetTenDayWeatherUseCase.Params("İstanbul"))
            .onStart { isLoading.value = true }
            .onCompletion { isLoading.value = false }
            .catch {
                shouldShowErrorMessage.value = true
            }
            .collect {
                tenDayWeatherUIModel.value = it
            }
    }
}