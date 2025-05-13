package com.example.mobile1project.thirdpartial.Location

import com.example.mobile1project.thirdpartial.Location.models.Location

class LocationRepository(private val apiService: LocationApiService) {
    suspend fun fetchLocations(): List<Location> {
        return apiService.getLocations()
    }
}