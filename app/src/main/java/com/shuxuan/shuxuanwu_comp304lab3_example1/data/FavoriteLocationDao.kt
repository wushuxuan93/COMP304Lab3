package com.shuxuan.shuxuanwu_comp304lab3_example1.data

import androidx.room.*

@Dao
interface FavoriteLocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(location: FavoriteLocation)

    @Query("SELECT * FROM favorite_locations")
    suspend fun getAll(): List<FavoriteLocation>

    @Delete
    suspend fun delete(location: FavoriteLocation)
}