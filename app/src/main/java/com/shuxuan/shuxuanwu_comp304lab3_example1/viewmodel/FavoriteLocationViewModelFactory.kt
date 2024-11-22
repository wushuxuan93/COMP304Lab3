package com.shuxuan.shuxuanwu_comp304lab3_example1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.shuxuan.shuxuanwu_comp304lab3_example1.data.FavoriteLocationDao
import com.shuxuan.shuxuanwu_comp304lab3_example1.repository.FavoriteLocationRepository


class FavoriteLocationViewModelFactory(
    private val dao: FavoriteLocationDao
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(FavoriteLocationViewModel::class.java)) {
            return FavoriteLocationViewModel(
                repository = FavoriteLocationRepository(dao)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}