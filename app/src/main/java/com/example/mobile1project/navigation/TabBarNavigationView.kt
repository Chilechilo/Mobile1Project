package com.example.mobile1project.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.mobile1project.firstpartial.FirstPartialView
import com.example.mobile1project.ids.IdsView
import com.example.mobile1project.ids.IMC.views.IMCView
import com.example.mobile1project.ids.Sum.Views.SumView
import com.example.mobile1project.ids.login.views.LoginView
import com.example.mobile1project.navigation.ScreenNavigation
import com.example.mobile1project.secondpartial.SecondPartialView
import com.example.mobile1project.temperature.views.TempView
import com.example.mobile1project.thirdpartial.Examen3.views.Examen3Screen
import com.example.mobile1project.thirdpartial.Location.LocationListScreen
import com.example.mobile1project.thirdpartial.Restaurants.viewmodels.RestaurantsViewModel
import com.example.mobile1project.thirdpartial.Restaurants.views.RestaurantDetailsView
import com.example.mobile1project.thirdpartial.Restaurants.views.RestaurantsScreen
import com.example.mobile1project.thirdpartial.Student.views.StudentView
import com.example.mobile1project.thirdpartial.ThirdPartialView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun TabBarNavigationView(navController: NavHostController = rememberNavController()) {
    val items = listOf(
        ScreenNavigation.Ids,
        ScreenNavigation.FirstPartial,
        ScreenNavigation.SecondPartial,
        ScreenNavigation.ThirdPartial
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            if (currentRoute != screen.route) {
                                navController.navigate(screen.route)
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ScreenNavigation.Ids.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(ScreenNavigation.Ids.route) { IdsView(navController) }
            composable(ScreenNavigation.FirstPartial.route) { FirstPartialView() }
            composable(ScreenNavigation.SecondPartial.route) { SecondPartialView() }
            composable(ScreenNavigation.ThirdPartial.route) { ThirdPartialView(navController) }
            composable(ScreenNavigation.IMC.route) { IMCView() }
            composable(ScreenNavigation.Login.route) { LoginView() }
            composable(ScreenNavigation.Sum.route) { SumView() }
            composable(ScreenNavigation.Temperature.route) { TempView() }
            composable(ScreenNavigation.StudentList.route) { StudentView() }
            composable(ScreenNavigation.Locations.route) { LocationListScreen() }
            composable(ScreenNavigation.Examen3.route) { Examen3Screen() }
            composable(ScreenNavigation.Restaurants.route) { RestaurantsScreen(navController) }
            composable(
                route = "restaurantDetail/{restaurantName}",
                arguments = listOf(navArgument("restaurantName") { type = NavType.StringType })
            ) { backStackEntry ->
                val restaurantNameEncoded = backStackEntry.arguments?.getString("restaurantName") ?: ""
                val restaurantName = URLDecoder.decode(restaurantNameEncoded, StandardCharsets.UTF_8.name())
                val viewModel: RestaurantsViewModel = viewModel()
                val restaurantList = viewModel.restaurants.collectAsState().value
                val restaurant = restaurantList.find { it.name == restaurantName }

                restaurant?.let {
                    RestaurantDetailsView(it)
                } ?: run {
                    Text(text = "Restaurante no encontrado", modifier = Modifier.padding(16.dp))
                }
            }

        }
    }
}
