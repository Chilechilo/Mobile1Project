package com.example.mobile1project.thirdpartial.Student.viewmodels

import androidx.lifecycle.ViewModel
import com.example.mobile1project.thirdpartial.Student.models.Student
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StudentViewModel : ViewModel() {
    private val _students = MutableStateFlow(
        listOf(
            Student("Jesús Omar Acuña Martínez"),
            Student("Alejandro Carrasco Maldonado"),
            Student("Ian Alejandro Corral Marín"),
            Student("Sara Escamilla Enriquez"),
            Student("Luis Angel Hernandez Corrales"),
            Student("Jose Ricardo Holguín Chiquito"),
            Student("Felix Elias Neder Samaniego"),
            Student("Jorge Parra Hidalgo"),
            Student("Yajahira Payán Palma"),
            Student("Miguel Dario Ruiz Olvera"),
            Student("Manuel Vito Saenz Montes")

        )
    )
    val students: StateFlow<List<Student>> = _students
}