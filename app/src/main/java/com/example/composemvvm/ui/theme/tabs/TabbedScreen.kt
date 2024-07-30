package com.example.composemvvm.ui.theme.tabs

import HomeScreen
import SettingsScreen
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.composemvvm.ui.theme.Purple80
import com.example.composemvvm.ui.theme.datastore.IS_LOGGED_IN
import com.example.composemvvm.ui.theme.datastore.dataStore
import com.example.composemvvm.ui.theme.datastore.setValue
import com.example.composemvvm.ui.theme.defaultDarkBlue

import com.example.composemvvm.ui.theme.navigateSingleTopTo
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabbedScreen(onMainNavigationClick: () -> Unit) {
    val navController = rememberNavController()
    val context = LocalContext.current.applicationContext
    val tabs = listOf(TabViewScreens.Home, TabViewScreens.Settings, TabViewScreens.Profile)
    val tabBarNames = listOf("Home", "Settings", "Profile")

    val selectedTabIndex = remember { mutableIntStateOf(0) }

    val showNavigationHomeIcon = remember {
        mutableStateMapOf<String, Any>(
            "showIcon" to false,
            "showTitle" to "Home"
        )
    }
    val showNavigationSettingsIcon = remember {
        mutableStateMapOf<String, Any>(
            "showIcon" to false,
            "showTitle" to "Settings"
        )
    }
    val showNavigationProfileIcon = remember {
        mutableStateMapOf<String, Any>(
            "showIcon" to false,
            "showTitle" to "Profile"
        )
    }
    val onClick: (value: Boolean, title: String?) -> Unit = { value, title ->
        when (selectedTabIndex.intValue) {
            1 -> {
                showNavigationSettingsIcon["showIcon"] = value
                showNavigationSettingsIcon["showTitle"] = title ?: ""
            }

            2 -> {
                showNavigationProfileIcon["showIcon"] = value
                showNavigationProfileIcon["showTitle"] = title ?: ""
            }

            0 -> {
                showNavigationHomeIcon["showIcon"] = value
                showNavigationHomeIcon["showTitle"] = title ?: ""
            }
        }
    }

    val onClickForHomeChild = remember {
        mutableStateOf({})
    }

    val onClickForProfileChild = remember {
        mutableStateOf({})
    }

    val onClickForSettingsChild = remember {
        mutableStateOf({})
    }
    val coroutineScope = rememberCoroutineScope()
    val text = when (selectedTabIndex.intValue) {
        0 -> showNavigationHomeIcon["showTitle"].toString()
        2 -> showNavigationProfileIcon["showTitle"].toString()
        1 -> showNavigationSettingsIcon["showTitle"].toString()
        else -> ""
    }
    Scaffold(

        topBar = {

            TopAppBar(title = {
                Text(
                    text = text
                )
            }, navigationIcon = {
                if ((selectedTabIndex.intValue == 0 && showNavigationHomeIcon["showIcon"] == true) ||
                    (selectedTabIndex.intValue == 2 && showNavigationProfileIcon["showIcon"] == true) ||
                    (selectedTabIndex.intValue == 1 && showNavigationSettingsIcon["showIcon"] == true)
                ) {
                    Icon(Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "navigation",
                        tint = Color.White,
                        modifier = Modifier.clickable {
                            onClick(false, tabBarNames[selectedTabIndex.intValue])
                            when (selectedTabIndex.intValue) {
                                0 -> onClickForHomeChild.value()
                                2 -> onClickForProfileChild.value()
                                1 -> onClickForSettingsChild.value()
                            }

                        }

                    )
                }
            },

                actions = {
                    Text(

                        text = "Log Out",
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp


                        ),
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable {
                                coroutineScope.launch {
                                    context.dataStore.setValue(
                                        IS_LOGGED_IN,
                                        false
                                    )
                                }

                                onMainNavigationClick()
                            },
                        color = Color.White
                    )

                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = defaultDarkBlue,
                    titleContentColor = Color.White
                )

            )

        }) {

            padding ->
        Column(
            modifier = Modifier.padding(
                start = padding.calculateStartPadding(LayoutDirection.Ltr),
                top = padding.calculateTopPadding()
            )
        ) {
            TabRow(selectedTabIndex = selectedTabIndex.intValue) {
                tabs.forEachIndexed { index, screen ->
                    Tab(selected = selectedTabIndex.intValue == index, onClick = {
                        selectedTabIndex.intValue = index
                        navController.navigateSingleTopTo(screen.route)
                    }, text = { Text(screen.route.uppercase()) })
                }

            }

            NavHost(navController, startDestination = TabViewScreens.Home.route) {
                composable(TabViewScreens.Home.route) {
                    HomeScreen(onNavigationClick = onClickForHomeChild, onClick)
                }
                composable(TabViewScreens.Settings.route) { SettingsScreen(onNavigationClick = onClickForSettingsChild) }
                composable(TabViewScreens.Profile.route) { ProfilesScreen(onNavigationClick = onClickForProfileChild) }


            }
        }
    }


}