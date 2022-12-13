package com.tolganacar.weatherforecast.viewmodel.currentweather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tolganacar.weatherforecast.model.CurrentWeatherResponseModel
import com.tolganacar.weatherforecast.service.WeatherForecastAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CurrentWeatherViewModel : ViewModel() {

    val currentWeather = MutableLiveData<CurrentWeatherResponseModel>()
    val shouldShowErrorMessage = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()

    private val weatherForecastAPIService = WeatherForecastAPIService()
    private val disposable = CompositeDisposable()

    fun setCurrentWeather(currentWeatherResponseModel: CurrentWeatherResponseModel) {
        currentWeather.value = currentWeatherResponseModel
    }

    fun getCurrentWeatherFromAPI() {
        isLoading.value = true

        disposable.add(
            weatherForecastAPIService.getIstanbulWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<CurrentWeatherResponseModel>() {
                    override fun onSuccess(response: CurrentWeatherResponseModel) {
                        currentWeather.value = response
                        shouldShowErrorMessage.value = false
                        isLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                        isLoading.value = false
                        shouldShowErrorMessage.value = true
                        e.printStackTrace()
                    }
                }
                )
        )
    }
}