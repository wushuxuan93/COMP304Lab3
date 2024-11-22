package com.shuxuan.shuxuanwu_comp304lab3_example1.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteLocation::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteLocationDao(): FavoriteLocationDao
}


