package com.shuxuan.shuxuanwu_comp304lab3_example1

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import com.shuxuan.shuxuanwu_comp304lab3_example1.data.AppDatabase
import com.shuxuan.shuxuanwu_comp304lab3_example1.navigation.AppNavGraph
import com.shuxuan.shuxuanwu_comp304lab3_example1.ui.theme.ShuxuanWu_COMP304Lab3_Example1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShuxuanWu_COMP304Lab3_Example1Theme {
                AppNavGraph()
            }
        }
    }
}


class WeatherTrackerApp : Application() {
    companion object {
        lateinit var database: AppDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext, // Use applicationContext to avoid memory leaks
            AppDatabase::class.java,
            "weather_database"
        )
            .fallbackToDestructiveMigration() // drop and recreate the database if needed
            .build()
    }
}
