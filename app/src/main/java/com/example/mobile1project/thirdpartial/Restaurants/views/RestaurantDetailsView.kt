package com.example.mobile1project.thirdpartial.Restaurants.views

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobile1project.thirdpartial.Restaurants.models.Restaurant
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantDetailsView(restaurant: Restaurant) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(restaurant.name, fontSize = 18.sp) }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            val imageResId = context.resources.getIdentifier(
                restaurant.imgName.lowercase().replace(Regex("[^a-z0-9_]"), ""),
                "drawable",
                context.packageName
            )

            if (imageResId != 0) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = restaurant.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Imagen no disponible", color = Color.White)
                }
            }

            val latitude = restaurant.latitude.toDoubleOrNull() ?: 0.0
            val longitude = restaurant.longitude.toDoubleOrNull() ?: 0.0
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(LatLng(latitude, longitude), 15f)
            }

            GoogleMap(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                cameraPositionState = cameraPositionState
            ) {
                Marker(
                    state = MarkerState(position = LatLng(latitude, longitude)),
                    title = restaurant.name
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = {
                    val callIntent = Intent(Intent.ACTION_DIAL).apply {
                        data = Uri.parse("tel:${restaurant.phone}")
                    }
                    context.startActivity(callIntent)
                }) {
                    Icon(imageVector = Icons.Default.Phone, contentDescription = "Llamar")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Llamar")
                }

                Button(onClick = {
                    val siteIntent = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse(restaurant.webSite)
                    }
                    context.startActivity(siteIntent)
                }) {
                    Icon(imageVector = Icons.Default.Language, contentDescription = "Sitio Web")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Sitio Web")
                }
            }
        }
    }
}