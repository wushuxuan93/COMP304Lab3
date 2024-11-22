package com.shuxuan.shuxuanwu_comp304lab3_example1.model

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@InternalSerializationApi @Serializable
data class WeatherResponse(
    @SerialName("name") val name: String,
    @SerialName("main") val main: Main,
    @SerialName("weather") val weather: List<Weather>
)

@InternalSerializationApi @Serializable
data class Main(
    @SerialName("temp") val temp: Double,
    @SerialName("humidity") val humidity: Int
)

@InternalSerializationApi @Serializable
data class Weather(
    @SerialName("description") val description: String
)
