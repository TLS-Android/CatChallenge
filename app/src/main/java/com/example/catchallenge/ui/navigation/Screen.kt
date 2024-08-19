package com.example.catchallenge.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val icon: ImageVector? = null, val label: String) {
    object Overview : Screen("overview", Icons.Default.Home, "Cats List")
    object Favourites : Screen("fav", Icons.Default.Search, "Favourites")
    object Detail : Screen("detail", null, "Detail")
}