package com.example.mobile1project.thirdpartial

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobile1project.navigation.ScreenNavigation


@Composable
fun ThirdPartialView(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Tercero Parcial Moviles I",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { navController.navigate(ScreenNavigation.StudentList.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir a Lista de Estudiantes")
        }
        Button(
            onClick = { navController.navigate(ScreenNavigation.Locations.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir a Ubicaciones")
        }
        Button(
            onClick = { navController.navigate(ScreenNavigation.Examen3.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir a Examen Parcial 3")
        }
        Button(
            onClick = { navController.navigate(ScreenNavigation.Restaurants.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cargar Restaurantes")
        }
    }
}