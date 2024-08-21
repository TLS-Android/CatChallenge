package com.example.catchallenge.ui.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.catchallenge.domain.model.CatBreed

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(
    modifier: Modifier,
    navController: NavHostController,
    breed: CatBreed,
    onToggleFavorite: () -> Unit,
) {
    Column(modifier = modifier.padding(48.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = breed.name,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            IconButton(onClick = onToggleFavorite) {
                Icon(
                    imageVector = if (breed.isFavourite) Icons.Filled.Star else Icons.Outlined.Star,
                    contentDescription = if (breed.isFavourite)
                        "Remove from favorites" else "Add to favorites"
                )
            }
        }
        Spacer(modifier = Modifier.height(48.dp))
        GlideImage(
            model = breed.imageUrl,
            contentDescription = breed.name,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(32.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Name: ${breed.name}",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Origin: ${breed.origin}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Temperament: ${breed.temperament}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = breed.description ?: "n/a")
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_4_XL)
@Composable
fun DetailScreenPreview() {
    DetailScreen(
        modifier = Modifier,
        navController = rememberNavController(),
        breed = CatBreed(
            id = "1",
            isFavourite = true,
            name = "Siamese",
            origin = "Thailand",
            temperament = "Affectionate, social, playful, and intelligent",
            description = "The Siamese cat is one of the first distinctly " +
                    "recognized breeds of Asian cat.",
            imageUrl = "https://cdn2.thecatapi.com/images/2v0.jpg",
        ),
        onToggleFavorite = {  }
    )
}
