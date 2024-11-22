package com.shuxuan.shuxuanwu_comp304lab3_example1.api

import com.shuxuan.shuxuanwu_comp304lab3_example1.model.WeatherResponse
import kotlinx.serialization.InternalSerializationApi
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @OptIn(InternalSerializationApi::class)
    @GET("weather")
    suspend fun getWeatherByCity(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): WeatherResponse
}