package com.example.composemvvm.ui.theme

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composemvvm.ui.theme.authentication.LoginScreen
import com.example.composemvvm.ui.theme.authentication.RegisterScreen
import com.example.composemvvm.ui.theme.datastore.IS_LOGGED_IN
import com.example.composemvvm.ui.theme.tabs.TabbedScreen
import com.example.composemvvm.ui.theme.viewmodels.LoginViewModel
import com.example.composemvvm.ui.theme.viewmodels.RegisterViewModel

@Composable
fun AppScreen(navController: NavHostController, destination: String) {
    NavHost(
        navController = navController, startDestination = destination,


        ) {


        composable(route = "tabbedScreen") {
            TabbedScreen(onMainNavigationClick = {
                navController.popBackStack()
                navController.navigateSingleTopTo("loginScreen")
            })
        }

        composable(route = "loginScreen") {
            val viewModel: LoginViewModel = hiltViewModel()
            LoginScreen(viewModel,
                onLoginCLick = {
                    navController.popBackStack()
                    navController.navigateSingleTopTo("tabbedScreen")
                },
                onRegisterCLick = {
                    navController.popBackStack()
                    navController.navigateSingleTopTo("registerScreen")
                }
            )
        }

        composable(route = "registerScreen") {
            val viewModel: RegisterViewModel = hiltViewModel()
            RegisterScreen(viewModel, onRegisterClick = {
                navController.popBackStack()
                navController.navigateSingleTopTo("loginScreen")
            })
        }


    }


}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }
