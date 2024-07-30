package com.example.composemvvm.ui.theme.bottomnavigationtabs

sealed class BottomTabsViewScreens(val route :String) {

    data object Users : BottomTabsViewScreens("users")

    data object Messages : BottomTabsViewScreens("messages")

}
