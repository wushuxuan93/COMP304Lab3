package com.shuxuan.shuxuanwu_comp304lab3_example1.repository

import com.shuxuan.shuxuanwu_comp304lab3_example1.data.FavoriteLocation
import com.shuxuan.shuxuanwu_comp304lab3_example1.data.FavoriteLocationDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoriteLocationRepository(private val dao: FavoriteLocationDao) {

    suspend fun addLocation(location: FavoriteLocation) {
        withContext(Dispatchers.IO) {
            dao.insert(location)
        }
    }

    suspend fun getLocations(): List<FavoriteLocation> {
        return withContext(Dispatchers.IO) {
            dao.getAll()
        }
    }

    suspend fun deleteLocation(location: FavoriteLocation) {
        withContext(Dispatchers.IO) {
            dao.delete(location)
        }
    }
}