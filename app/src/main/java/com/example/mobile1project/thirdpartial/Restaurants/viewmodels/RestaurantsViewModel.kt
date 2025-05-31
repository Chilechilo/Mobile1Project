package com.example.mobile1project.thirdpartial.Restaurants.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile1project.thirdpartial.Restaurants.RestaurantsApiService
import com.example.mobile1project.thirdpartial.Restaurants.RestaurantsRepository
import com.example.mobile1project.thirdpartial.Restaurants.models.Restaurant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class RestaurantsViewModel : ViewModel() {

    private val apiService = RestaurantsApiService.create()
    private val repository = RestaurantsRepository(apiService)

    private val _restaurants = MutableStateFlow<List<Restaurant>>(emptyList())
    val restaurants = _restaurants.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    init {
        loadRestaurants()
    }

    private fun loadRestaurants() {
        viewModelScope.launch {
            try {
                _restaurants.value = repository.fetchRestaurants()
            } catch (e: HttpException) {
                _errorMessage.value = when (e.code()) {
                    404 -> Log.d("Error 404", "No encontrado").toString()
                    500 -> Log.d("Error 500", "Error del servidor").toString()
                    else -> "Error HTTP: ${e.message()}"
                }
            } catch (e: IOException) {
                _errorMessage.value = "Sin conexión a internet"
            } catch (e: Exception) {
                _errorMessage.value = "Error desconocido: ${e.message}"
            }
        }
    }
}