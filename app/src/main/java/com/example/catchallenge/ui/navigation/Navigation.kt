package com.example.catchallenge.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.catchallenge.ui.screens.detail.DetailScreen
import com.example.catchallenge.ui.screens.favourites.FavouritesScreen
import com.example.catchallenge.ui.screens.overview.OverviewScreen

@Composable
fun NavigationComponent(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    innerPadding: PaddingValues
) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPaddingValues ->
        Navigation(
            navController = navController,
            modifier = Modifier.padding(innerPaddingValues)
        )
    }
}


@Composable
fun Navigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = Screen.Overview.route) {
        composable(Screen.Overview.route) { OverviewScreen() }
        composable(Screen.Favourites.route) { FavouritesScreen() }
        composable(Screen.Detail.route) { DetailScreen() }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        Screen.Overview,
        Screen.Favourites,
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { screen ->
            NavigationBarItem(
                icon = { if (screen.icon != null) Icon(screen.icon, contentDescription = screen.label) },
                label = { Text(screen.label) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}