package com.shuxuan.shuxuanwu_comp304lab3_example1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shuxuan.shuxuanwu_comp304lab3_example1.model.WeatherResponse
import com.shuxuan.shuxuanwu_comp304lab3_example1.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.InternalSerializationApi

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    @OptIn(InternalSerializationApi::class)
    private val _weatherState = MutableStateFlow<Result<WeatherResponse>?>(null)
    @OptIn(InternalSerializationApi::class)
    val weatherState = _weatherState.asStateFlow()

    @OptIn(InternalSerializationApi::class)
    fun getWeather(cityName: String, apiKey: String) {
        viewModelScope.launch {
            val result = repository.fetchWeather(cityName, apiKey)
            _weatherState.value = result
        }
    }
}