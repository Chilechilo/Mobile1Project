package com.example.mobile1project.thirdpartial.Restaurants

import com.example.mobile1project.thirdpartial.Restaurants.models.Restaurant

class RestaurantsRepository(private val apiService: RestaurantsApiService) {
    suspend fun fetchRestaurants(): List<Restaurant> {
        return apiService.getRestaurants()
    }
}