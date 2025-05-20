package com.example.mobile1project.thirdpartial.Examen3.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile1project.thirdpartial.Examen3.Examen3ApiService
import com.example.mobile1project.thirdpartial.Examen3.Examen3Repository
import com.example.mobile1project.thirdpartial.Examen3.models.Student
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import retrofit2.HttpException

class Examen3ViewModel : ViewModel() {

    private val apiService = Examen3ApiService.create()
    private val repository = Examen3Repository(apiService)

    private val _students = MutableStateFlow<List<Student>>(emptyList())
    val students = _students.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    init {
        loadStudents()
    }

    private fun loadStudents() {
        viewModelScope.launch {
            try {
                _students.value = repository.fetchStudents()
            } catch (e: HttpException) {
                _errorMessage.value = when (e.code()) {
                    404 -> Log.d("Error 404", "Error 404 No encontrado").toString()
                    500 -> Log.d("Error 500", "Error 500 Error Servidor").toString()
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