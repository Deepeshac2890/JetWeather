package com.example.jetweather.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetweather.screens.mainScreen.MainScreen
import com.example.jetweather.screens.WeatherSplashScreen
import com.example.jetweather.screens.aboutScreen.AboutScreen
import com.example.jetweather.screens.favoriteScreen.FavoriteScreen
import com.example.jetweather.screens.mainScreen.MainViewModel
import com.example.jetweather.screens.searchScreen.SearchScreen
import com.example.jetweather.screens.settingsScreen.SettingsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.name
    ) {

        composable(Screens.SplashScreen.name) {
            WeatherSplashScreen(navController = navController)
        }

        composable("${Screens.MainScreen.name}/{city}") { it ->
            it.arguments!!.getString("city").let { city ->
                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(navController = navController, viewModel = mainViewModel, city = city)
            }
        }

        composable(Screens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }

        composable(Screens.AboutScreen.name) {
            AboutScreen(navController = navController)
        }

        composable(Screens.FavoriteScreen.name) {
            FavoriteScreen(navController = navController)
        }

        composable(Screens.SettingsScreen.name) {
            SettingsScreen(navController = navController)
        }
    }
}

