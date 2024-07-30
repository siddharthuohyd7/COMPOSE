package com.example.composemvvm.ui.theme.tabs

sealed class TabViewScreens(val route :String) {

    data object Home :TabViewScreens("home")

    data object Settings :TabViewScreens("settings")

    data object Profile :TabViewScreens("profile")

}