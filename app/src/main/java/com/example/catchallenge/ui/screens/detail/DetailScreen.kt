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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.catchallenge.domain.model.CatBreed

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(
    modifier: Modifier,
    viewModel: DetailViewModel = hiltViewModel(),
    onToggleFavorite: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = modifier.padding(48.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = uiState.catBreed!!.name,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            IconButton(onClick = onToggleFavorite) {
                Icon(
                    imageVector = if (uiState.catBreed!!.isFavourite) Icons.Filled.Star else Icons.Outlined.Star,
                    contentDescription = if (uiState.catBreed!!.isFavourite)
                        "Remove from favorites" else "Add to favorites"
                )
            }
        }
        Spacer(modifier = Modifier.height(48.dp))
        GlideImage(
            model = uiState.catBreed!!.imageUrl,
            contentDescription = uiState.catBreed!!.name,
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
                text = "Name: ${uiState.catBreed!!.name}",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Origin: ${uiState.catBreed!!.origin}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Temperament: ${uiState.catBreed!!.temperament}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = uiState.catBreed!!.description ?: "n/a")
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_4_XL)
@Composable
fun DetailScreenPreview() {
    val sampleBreed = CatBreed(
        id = "1",
        name = "Siamese",
        origin = "Thailand",
        temperament = "Affectionate, social, playful, and intelligent",
        description = "The Siamese cat is one of the first distinctly " +
                "recognized breeds of Asian cat.",
    )

    DetailScreen(
        modifier = Modifier,
        viewModel = TODO(),
        onToggleFavorite = { },
    )
}
