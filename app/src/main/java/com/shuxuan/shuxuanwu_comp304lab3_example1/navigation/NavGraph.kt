package com.shuxuan.shuxuanwu_comp304lab3_example1.navigation

import com.shuxuan.shuxuanwu_comp304lab3_example1.ui.screens.HomeScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.shuxuan.shuxuanwu_comp304lab3_example1.ui.screens.DetailsScreen


@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        // Home Screen route
        composable("home") {
            HomeScreen(navController)
        }

        // Details Screen route
        composable(
            route = "details/{cityName}",
            arguments = listOf(navArgument("cityName") { type = NavType.StringType })
        ) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            DetailsScreen(
                cityName = cityName, // Pass only cityName
                navController = navController
            )
        }
    }
}