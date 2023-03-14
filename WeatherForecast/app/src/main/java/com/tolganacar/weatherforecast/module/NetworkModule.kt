package com.tolganacar.weatherforecast.module

import android.util.Log
import com.google.gson.Gson
import com.tolganacar.weatherforecast.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val CLIENT_TIME_OUT_SEC = 30L

    @Provides
    @Singleton
    fun provideOkHttpClient(
    ): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(CLIENT_TIME_OUT_SEC, TimeUnit.SECONDS)
            .readTimeout(CLIENT_TIME_OUT_SEC, TimeUnit.SECONDS)

        return httpClient.build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson().newBuilder().create()
    }

    @Provides
    @Singleton
    @OpenWeatherMapRetrofit
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_OPENWEATHERMAP)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(
                        object : Interceptor {
                            override fun intercept(chain: Interceptor.Chain): Response {
                                val request: Request = chain.request()
                                    .newBuilder()
                                    .build()
                                return chain.proceed(request)
                            }
                        }
                    )
                    .addNetworkInterceptor(
                        Interceptor { chain ->
                            val original = chain.request()
                            val requestBuilder = original.newBuilder()
                            val originalHttpUrl = chain.request().url
                            val newUrl = originalHttpUrl.newBuilder()
                                .addQueryParameter("appid", "d226094cc9cf2c082beaaee4d673c381")
                                .build()

                            val request = requestBuilder.url(newUrl).build()
                            val response = chain.proceed(request)
                            Log.e("request", request.headers.toString())
                            Log.e("Response Body", response.body!!.toString())

                            response
                        }
                    )
                    .build()
            )
            .build()
    }

    @Provides
    @Singleton
    @WeatherApiRetrofit
    fun provideWeatherApiRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_WEATHERAPI)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(
                        object : Interceptor {
                            override fun intercept(chain: Interceptor.Chain): Response {
                                val request: Request = chain.request()
                                    .newBuilder()
                                    .build()
                                return chain.proceed(request)
                            }
                        }
                    )
                    .addNetworkInterceptor(
                        Interceptor { chain ->
                            val original = chain.request()
                            val requestBuilder = original.newBuilder()
                            val originalHttpUrl = chain.request().url
                            val newUrl = originalHttpUrl.newBuilder()
                                .addQueryParameter("key", "abc1e2bd0ee7486484a110827232601")
                                .build()

                            val request = requestBuilder.url(newUrl).build()
                            val response = chain.proceed(request)
                            Log.e("request", request.headers.toString())
                            Log.e("Response Body", response.body!!.toString())

                            response
                        }
                    )
                    .build()
            )
            .build()
    }

}