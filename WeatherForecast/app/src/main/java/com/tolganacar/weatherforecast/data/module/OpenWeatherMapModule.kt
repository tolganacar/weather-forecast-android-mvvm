package com.tolganacar.weatherforecast.data.module

import com.tolganacar.weatherforecast.data.repository.OpenWeatherMapRepositoryImpl
import com.tolganacar.weatherforecast.data.service.OpenWeatherMapService
import com.tolganacar.weatherforecast.data.repository.OpenWeatherMapRepository
import com.tolganacar.weatherforecast.module.OpenWeatherMapRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OpenWeatherMapModule {

    @Provides
    @Singleton
    fun provideWeatherService(@OpenWeatherMapRetrofit retrofit: Retrofit): OpenWeatherMapService {
        return retrofit.create(OpenWeatherMapService::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(service: OpenWeatherMapService): OpenWeatherMapRepository {
        return OpenWeatherMapRepositoryImpl(service)
    }

}