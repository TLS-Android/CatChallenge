package com.example.catchallenge.ui.screens.detail

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.sharp.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.catchallenge.domain.model.CatBreed
import com.example.catchallenge.domain.model.CatBreedImageData

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(
    modifier: Modifier,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = modifier.padding(48.dp)) {
        uiState.catBreed?.let { catBreed ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = catBreed.name,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = {
                    viewModel.toggleFavorite(catBreed)
                }) {
                    Icon(
                        imageVector = if (catBreed.isFavourite)
                            Icons.Filled.Star else Icons.Sharp.Star,
                        contentDescription = if (catBreed.isFavourite)
                            "Remove from favorites" else "Add to favorites",
                        tint = if (catBreed.isFavourite) Color.Yellow else Color.Black,
                    )
                }
            }
            Spacer(modifier = Modifier.height(48.dp))
            GlideImage(
                model = catBreed.image?.url,
                contentDescription = "Cat Image",
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
                    text = "Name: ${catBreed.name}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Origin: ${catBreed.origin}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Temperament: ${catBreed.temperament}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = catBreed.description ?: "n/a")
            }
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
        image = CatBreedImageData(
            imageId = "123",
            url = "https://cdn2.thecatapi.com/images/OGTWqNNOt.jpg"
        ),
    )

    DetailScreen(
        modifier = Modifier,
        viewModel = TODO(),
    )
}
