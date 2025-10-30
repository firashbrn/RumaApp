package com.example.ruma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ruma.screen.RegisterLogin.RegisterScreen
import com.example.ruma.screen.RegisterLogin.loginScreen
import com.example.ruma.ui.theme.RUMATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RUMATheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RumaApp()
                }
            }
        }
    }
}

@Composable
fun RumaApp(modifier: Modifier = Modifier) {
    
}

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
        composable(Route.REGISTER) { (RegisterScreen(
            onBackToLogin = { -> nav.popBackStack() }
        )) }
        composable(Route.HOME) { /* TODO: dashboard modul Ruma */ }
    }
}