package com.shuxuan.shuxuanwu_comp304lab3_example1.repository
import com.shuxuan.shuxuanwu_comp304lab3_example1.api.RetrofitClient
import com.shuxuan.shuxuanwu_comp304lab3_example1.model.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.InternalSerializationApi

class WeatherRepository {
    private val apiService = RetrofitClient.apiService

    @OptIn(InternalSerializationApi::class)
    suspend fun fetchWeather(cityName: String, apiKey: String): Result<WeatherResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getWeatherByCity(cityName, apiKey)
                Result.success(response)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}