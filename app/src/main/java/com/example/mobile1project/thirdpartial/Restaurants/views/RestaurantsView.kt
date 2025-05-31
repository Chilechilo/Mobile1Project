package com.example.mobile1project.thirdpartial.Restaurants.views

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mobile1project.thirdpartial.Examen3.views.getDrawableIdOrDefault
import com.example.mobile1project.thirdpartial.Restaurants.viewmodels.RestaurantsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RestaurantsScreen(
    navController: NavHostController,
    viewModel: RestaurantsViewModel = viewModel()
) {
    val restaurants = viewModel.restaurants.collectAsState()
    val errorMessage = viewModel.errorMessage.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de restaurantes") }
            )
        }
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            errorMessage.value?.let { msg ->
                Text(text = msg, color = MaterialTheme.colorScheme.error)
                Spacer(modifier = Modifier.height(8.dp))
            }

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(restaurants.value) { restaurant ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate("restaurantDetail/${Uri.encode(restaurant.name)}")
                            },
                        shape = RoundedCornerShape(8.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column {
                            val imageRes = getDrawableIdOrDefault(
                                restaurant.imgName
                                    .substringBeforeLast('.')
                                    .lowercase()
                                    .replace(Regex("[^a-z0-9_]"), "")
                            )

                            Box {
                                if (imageRes != 0) {
                                    Image(
                                        painter = painterResource(id = imageRes),
                                        contentDescription = restaurant.name,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(200.dp),
                                        contentScale = ContentScale.Crop
                                    )
                                } else {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(200.dp)
                                            .background(Color.Gray),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text("Imagen no disponible", color = Color.White)
                                    }
                                }

                                Icon(
                                    imageVector = if (restaurant.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                    contentDescription = "Favorito",
                                    tint = if (restaurant.isFavorite) Color.Red else Color.White,
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .padding(12.dp)
                                        .size(24.dp)
                                )
                            }

                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = restaurant.name,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(text = "MX ${restaurant.fee} Delivery Fee · ${restaurant.delivery}")

                                Spacer(modifier = Modifier.height(4.dp))

                                Surface(
                                    color = Color.LightGray.copy(alpha = 0.9f),
                                    shape = RoundedCornerShape(4.dp)
                                ) {
                                    Text(
                                        text = "%.1f".format(restaurant.rating),
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
