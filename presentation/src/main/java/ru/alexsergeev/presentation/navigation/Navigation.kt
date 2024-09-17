package ru.alexsergeev.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.alexsergeev.presentation.ui.screens.CodeScreen
import ru.alexsergeev.presentation.ui.screens.EditProfileScreen
import ru.alexsergeev.presentation.ui.screens.InputPhoneNumberScreen
import ru.alexsergeev.presentation.ui.screens.MainScreen
import ru.alexsergeev.presentation.ui.screens.MessageListScreen
import ru.alexsergeev.presentation.ui.screens.RemoveProfileScreen
import ru.alexsergeev.presentation.ui.screens.SplashScreen
import ru.alexsergeev.presentation.ui.screens.UserProfileScreen

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
        composable("main_screen") {
            MainScreen(navController = navController)
        }
        composable("channel_item/{id}") {
            MessageListScreen(
                navController = navController,
                cid = it.arguments?.getString("id")!!
            )
        }
        composable("profile_screen") {
            UserProfileScreen(navController = navController)
        }
        composable("edit_profile_screen") {
            EditProfileScreen(navController = navController)
        }
        composable("remove_profile_screen") {
            RemoveProfileScreen(navController = navController)
        }
    }
}