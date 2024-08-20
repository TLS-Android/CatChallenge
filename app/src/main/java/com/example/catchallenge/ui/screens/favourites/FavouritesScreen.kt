package com.example.catchallenge.ui.screens.favourites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.catchallenge.ui.screens.overview.CatBreedItem

@Composable
fun FavouritesScreen(
    modifier: Modifier = Modifier,
    favCatBreeds: List<String> = listOf(
        "Breed 1", "Breed 2", "Breed 3", "Breed 4", "Breed 5", "Breed 6",
    ),
) {

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = modifier.padding(48.dp)) {
            Text(
                text = "Favourites",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(
                    top = 48.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                ),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(favCatBreeds) { breed ->
                    CatBreedItem(
                        breed,
                        isFavorite = false,
                        onFavoriteClick = {}
                    )
                }
            }
        }
    }




}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_4_XL)
@Composable
fun FavouritesScreenPreview() {
    FavouritesScreen()
}