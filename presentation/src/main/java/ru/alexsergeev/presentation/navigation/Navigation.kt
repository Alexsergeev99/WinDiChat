package ru.alexsergeev.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.alexsergeev.presentation.ui.screens.CodeScreen
import ru.alexsergeev.presentation.ui.screens.InputPhoneNumberScreen
import ru.alexsergeev.presentation.ui.screens.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "splash_screen"
    ) {
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }
        composable("auth_screen") {
            InputPhoneNumberScreen(navController = navController)
        }
        composable("code_screen") {
            CodeScreen(navController = navController)
        }
    }
}