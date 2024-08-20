package com.example.catchallenge.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val icon: ImageVector? = null, val label: String) {
    object Overview : Screen("overview", Icons.Default.Apps, "Cats List")
    object Detail : Screen("detail/{breedId}", null, "Detail")
    object Favourites : Screen("fav", Icons.Default.Star, "Favourites")
}