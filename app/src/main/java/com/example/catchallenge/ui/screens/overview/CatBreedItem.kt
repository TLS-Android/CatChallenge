package com.example.catchallenge.ui.screens.overview

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.catchallenge.domain.model.CatBreed
import com.example.catchallenge.ui.navigation.Screen

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CatBreedItem(
    breed: CatBreed,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    navController: NavController
) {
    Log.d("CatBreedItem", "url: ${breed.url}")
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
                .clickable {
                    navController.navigate(Screen.Detail.route + "/${breed.id}")
                }
        ) {
            GlideImage(
                model = breed.url,
                contentDescription = breed.description,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(16f / 9f),
                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = onFavoriteClick,
                modifier = Modifier
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Star else Icons.Outlined.Star,
                    contentDescription = "Favorite",
                    tint = if (isFavorite) Color.Yellow else Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
        Text(breed.name)
    }
}