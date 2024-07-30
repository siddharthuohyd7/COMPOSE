package com.example.composemvvm.ui.theme.bottomnavigationtabs

import Messages
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Message
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composemvvm.ui.theme.navigateSingleTopTo

@Composable
fun BottomNavigationTabbedScreen() {

    val navController = rememberNavController()
    val navigationSelectedItem = remember {
        mutableIntStateOf(0)
    }
    Scaffold(bottomBar = {
        BottomNavigationBar(
            navController,
            navigationSelectedItem
        )
    }) { padding ->
        NavigationComponent(navController = navController, modifier = Modifier.padding(padding))
    }

}

@Composable
fun BottomNavigationBar(navController: NavHostController, state: MutableState<Int>) {

    val items = listOf("Users", "Messages")
    val icons = listOf(Icons.Filled.Face, Icons.Default.Message)
    val listTabRoutes = listOf(BottomTabsViewScreens.Users, BottomTabsViewScreens.Messages)
    NavigationBar(
        containerColor = Color.White,
        contentColor = Color.Gray,
        tonalElevation = 8.dp

    ) {
        items.forEachIndexed { index, item ->

            NavigationBarItem(
                selected = state.value == index,
                onClick = {
                    state.value = index


                    navController.navigateSingleTopTo(listTabRoutes[index].route)

                },
                icon = { Icon(imageVector = icons[index], contentDescription = "image $index") },

                label = { Text(item) }

            )

        }
    }
}

@Composable
fun NavigationComponent(navController: NavHostController, modifier: Modifier) {
    NavHost(navController = navController, startDestination = "users", modifier = modifier) {
        composable("messages") {
            Messages()
        }
        composable("users") {
            Users()
        }
    }
}