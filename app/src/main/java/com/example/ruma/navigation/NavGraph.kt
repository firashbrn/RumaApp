package com.example.ruma.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.ruma.screen.RegisterLogin.RegisterScreen
import com.example.ruma.screen.RegisterLogin.loginScreen


object Route {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home" // placeholder
}

@Composable
fun RumaNavGraph() {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = Route.LOGIN) {
        composable(Route.LOGIN) {
            loginScreen(
                onRegister = { nav.navigate(Route.REGISTER) },
                onLoginSuccess = { nav.navigate(Route.HOME) { popUpTo(Route.LOGIN) { inclusive = true } } }
            )
        }
        composable(Route.REGISTER) {
            RegisterScreen(
                onBackToLogin = { nav.popBackStack() }
            )
        }
        composable(Route.HOME) { /* TODO: dashboard modul Ruma */ }
    }
}