package com.example.mobile1project.thirdpartial.Examen3.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobile1project.thirdpartial.Examen3.viewmodels.Examen3ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Examen3Screen(viewModel: Examen3ViewModel = viewModel()) {
    val students = viewModel.students.collectAsState()
    val errorMessage = viewModel.errorMessage.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Estudiantes") }
            )
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            errorMessage.value?.let { msg ->
                Text(text = msg, color = MaterialTheme.colorScheme.error)
                Spacer(modifier = Modifier.height(8.dp))
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(students.value) { student ->
                    Card(
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            val imageRes = getDrawableIdOrDefault(
                                student.imageName
                                    .substringBeforeLast('.')
                                    .lowercase()
                                    .replace(Regex("[^a-z0-9_]"), "")
                            )

                            Image(
                                painter = painterResource(id = imageRes),
                                contentDescription = null,
                                modifier = Modifier.size(64.dp),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(text = "Nombre: ${student.name}")
                                Text(text = "Matrícula: ${student.studentId}")
                                Text(text = "Frase: ${student.quote}")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun getDrawableIdOrDefault(imageName: String): Int {
    val context = LocalContext.current
    val resId = context.resources.getIdentifier(imageName, "drawable", context.packageName)
    return if (resId != 0) resId else android.R.drawable.stat_notify_error
}