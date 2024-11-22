package com.shuxuan.shuxuanwu_comp304lab3_example1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.shuxuan.shuxuanwu_comp304lab3_example1.model.WeatherResponse
import com.shuxuan.shuxuanwu_comp304lab3_example1.repository.WeatherRepository
import com.shuxuan.shuxuanwu_comp304lab3_example1.viewmodel.WeatherViewModel
import com.shuxuan.shuxuanwu_comp304lab3_example1.viewmodel.WeatherViewModelFactory
import kotlinx.serialization.InternalSerializationApi


import androidx.compose.material.icons.filled.ArrowBack

@OptIn(ExperimentalMaterial3Api::class, InternalSerializationApi::class)
@Composable
fun DetailsScreen(
    navController: NavController,
    cityName: String,
    weatherViewModel: WeatherViewModel = viewModel(
        factory = WeatherViewModelFactory(WeatherRepository())
    )
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val weatherState by weatherViewModel.weatherState.collectAsState()
    val apiKey = "52c7b3ea460e83d362a598309ee31c69"

    LaunchedEffect(cityName) {
        weatherViewModel.getWeather(cityName, apiKey)
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Weather Details") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack() // Navigate back when clicking the back button
                    }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { padding ->
            when {
                weatherState == null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                weatherState!!.isSuccess -> {
                    weatherState?.getOrNull()?.let { weatherData ->
                        WeatherDetails(weatherData, padding)
                    }
                }
                weatherState!!.isFailure -> {
                    val error = weatherState?.exceptionOrNull()
                    LaunchedEffect(snackbarHostState) {
                        snackbarHostState.showSnackbar(
                            message = "Error fetching weather data: ${error?.localizedMessage ?: "Unknown error"}"
                        )
                    }
                }
            }
        }
    )
}

@OptIn(InternalSerializationApi::class)
@Composable
fun WeatherDetails(weatherResponse: WeatherResponse, padding: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(padding)
            .padding(16.dp)
    ) {
        Text("City: ${weatherResponse.name}", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Temperature: ${weatherResponse.main.temp}°C", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text("Humidity: ${weatherResponse.main.humidity}%", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            "Description: ${weatherResponse.weather.firstOrNull()?.description ?: "No description"}",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
