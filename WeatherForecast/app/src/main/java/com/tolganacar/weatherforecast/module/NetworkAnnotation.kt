package com.tolganacar.weatherforecast.module

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OpenWeatherMapRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class WeatherApiRetrofit
