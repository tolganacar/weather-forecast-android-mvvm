package com.tolganacar.weatherforecast.data.module

import com.tolganacar.weatherforecast.data.repository.WeatherApiRepository
import com.tolganacar.weatherforecast.data.repository.WeatherApiRepositoryImpl
import com.tolganacar.weatherforecast.data.service.WeatherApiService
import com.tolganacar.weatherforecast.module.WeatherApiRetrofit

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherApiModule {

    @Provides
    @Singleton
    fun provideWeatherApiService(@WeatherApiRetrofit retrofit: Retrofit): WeatherApiService {
        return retrofit.create(WeatherApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(service: WeatherApiService): WeatherApiRepository {
        return WeatherApiRepositoryImpl(service)
    }

}