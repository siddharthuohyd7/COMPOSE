package com.example.composemvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.navigation.compose.rememberNavController

import com.example.composemvvm.ui.theme.AppScreen
import com.example.composemvvm.ui.theme.ComposeMVVMTheme

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            val isLoggedIn = intent.getBooleanExtra("is_logged_in", false)
            val destination: String = if (isLoggedIn) "tabbedScreen" else "loginScreen"
            ComposeMVVMTheme {
                AppScreen(navController, destination)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

