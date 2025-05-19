package com.example.mobile1project.thirdpartial.Examen3

import com.example.mobile1project.thirdpartial.Examen3.models.Student

class Examen3Repository(private val apiService: Examen3ApiService) {
    suspend fun fetchStudents(): List<Student> {
        return apiService.getStudents()
    }
}