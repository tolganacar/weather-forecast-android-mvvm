package com.tolganacar.weatherforecast.data.module

import com.tolganacar.weatherforecast.data.repository.WeatherThreeDaysRepository
import com.tolganacar.weatherforecast.data.repository.WeatherThreeDaysRepositoryImpl
import com.tolganacar.weatherforecast.data.service.WeatherThreeDaysService
import com.tolganacar.weatherforecast.module.WeatherThreeDaysRetrofit
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
    fun provideWeatherApiService(@WeatherThreeDaysRetrofit retrofit: Retrofit): WeatherThreeDaysService {
        return retrofit.create(WeatherThreeDaysService::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(service: WeatherThreeDaysService): WeatherThreeDaysRepository {
        return WeatherThreeDaysRepositoryImpl(service)
    }

}