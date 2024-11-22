package com.shuxuan.shuxuanwu_comp304lab3_example1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shuxuan.shuxuanwu_comp304lab3_example1.data.FavoriteLocation
import com.shuxuan.shuxuanwu_comp304lab3_example1.repository.FavoriteLocationRepository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoriteLocationViewModel(private val repository: FavoriteLocationRepository) : ViewModel() {

    private val _locations = MutableStateFlow<List<FavoriteLocation>>(emptyList())
    val locations = _locations.asStateFlow()

    init {
        loadLocations()
    }

    fun loadLocations() {
        viewModelScope.launch {
            _locations.value = repository.getLocations()
        }
    }

    fun addLocation(location: FavoriteLocation) {
        viewModelScope.launch {
            repository.addLocation(location)
            loadLocations()
        }
    }

    fun deleteLocation(location: FavoriteLocation) {
        viewModelScope.launch {
            repository.deleteLocation(location)
            loadLocations()
        }
    }
}