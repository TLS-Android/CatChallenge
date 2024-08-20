package com.example.catchallenge.ui.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.catchallenge.domain.model.CatBreed

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(
    breed: CatBreed,
    onToggleFavorite: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
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
        Spacer(modifier = Modifier.height(16.dp))
        GlideImage(
            model = breed.imageUrl,
            contentDescription = breed.name,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Name: ${breed.name}")
            Spacer(modifier = Modifier.height(4.dp))
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
        breed = CatBreed(
            id = "1",
            isFavourite = true,
            name = "Siamese",
            temperament = "Affectionate, social, playful, and intelligent",
            description = "The Siamese cat is one of the first distinctly " +
                    "recognized breeds of Asian cat.",
            imageUrl = "https://cdn2.thecatapi.com/images/2v0.jpg",
        ),
        onToggleFavorite = {  }
    )
}
