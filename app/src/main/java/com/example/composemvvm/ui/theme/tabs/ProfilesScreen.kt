package com.example.composemvvm.ui.theme.tabs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composemvvm.ui.theme.bottomnavigationtabs.BottomNavigationTabbedScreen

@Composable
fun ProfilesScreen(onNavigationClick: MutableState<() -> Unit>) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "bottomBarTabbedScreen") {
        composable("bottomBarTabbedScreen") {
          BottomNavigationTabbedScreen()
        }
    }
}