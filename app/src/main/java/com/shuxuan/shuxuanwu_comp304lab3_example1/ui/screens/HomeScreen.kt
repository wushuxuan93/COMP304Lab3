package com.shuxuan.shuxuanwu_comp304lab3_example1.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shuxuan.shuxuanwu_comp304lab3_example1.WeatherTrackerApp
import com.shuxuan.shuxuanwu_comp304lab3_example1.data.FavoriteLocation
import com.shuxuan.shuxuanwu_comp304lab3_example1.viewmodel.FavoriteLocationViewModelFactory
import com.shuxuan.shuxuanwu_comp304lab3_example1.viewmodel.FavoriteLocationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    favoriteLocationViewModel: FavoriteLocationViewModel = viewModel(
        factory = FavoriteLocationViewModelFactory(
            WeatherTrackerApp.database.favoriteLocationDao()
        )
    )
) {
    val locations by favoriteLocationViewModel.locations.collectAsState()
    var cityName by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Weather Tracker") }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = cityName,
                    onValueChange = { cityName = it },
                    label = { Text("Enter City Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        if (cityName.isNotBlank()) {
                            favoriteLocationViewModel.addLocation(
                                FavoriteLocation(cityName = cityName.trim())
                            )
                            cityName = ""
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Add to Favorites")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Favorite Locations",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn {
                    items(locations) { location ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate("details/${location.cityName}")
                                }
                                .padding(vertical = 8.dp)
                        ) {
                            Text(
                                text = location.cityName,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }
        })}

