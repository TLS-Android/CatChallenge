package com.example.catchallenge.ui.screens.favourites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.catchallenge.domain.model.CatBreed
import com.example.catchallenge.ui.screens.overview.CatBreedItem

@Composable
fun FavouritesScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    favCatBreeds: List<CatBreed> = listOf(
        CatBreed("1", "Breed 1"),
        CatBreed("2", "Breed 2"),
        CatBreed("3", "Breed 3"),
        CatBreed("4", "Breed 4"),
        CatBreed("5", "Breed 5"),
        CatBreed("6", "Breed 6"),
        CatBreed("7", "Breed 7"),
        CatBreed("8", "Breed 8"),
        CatBreed("9", "Breed 9"),
        CatBreed("10", "Breed 10"),
    )
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = modifier.padding(48.dp)) {
            Text(
                text = "Favourites",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

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
                        onFavoriteClick = {},
                        navController = navController
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
    FavouritesScreen(
        navController = rememberNavController()
    )
}