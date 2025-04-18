package com.example.mobile1project.temperature.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.res.stringResource
import com.example.mobile1project.R
import com.example.mobile1project.temperature.viewmodels.TempViewModel

@Composable
fun TempView(tempViewModel: TempViewModel = viewModel()) {
    val Cel by tempViewModel.Cel
    val Fahr by tempViewModel.Fahr
    val msgerror by tempViewModel.msgerror
    val errorMsg = stringResource(id = R.string.error_invalid_input)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.templogo),
            contentDescription = "",
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
        )

        Text(
            text = stringResource(id = R.string.temp_label),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        TextField(
            value = Cel,
            onValueChange = {
                tempViewModel.Cel.value = it
            },
            label = { Text(text = stringResource(id = R.string.enter_cel)) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            tempViewModel.convertToFahrenheit(errorMsg)
        }) {
            Text(text = stringResource(id = R.string.calculate_temp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (msgerror.isEmpty()) {
            Text(
                text = "F°: $Fahr°",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 200.dp)
            )
        } else {
            Text(text = msgerror, color = MaterialTheme.colorScheme.error)
        }
    }
}
