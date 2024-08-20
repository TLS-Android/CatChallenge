package com.example.catchallenge.ui.screens.overview

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun OverviewScreen(
    modifier: Modifier = Modifier,
    catBreeds: List<String> = listOf(
        "Breed 1", "Breed 2", "Breed 3", "Breed 4", "Breed 5", "Breed 6",
        "Breed 7", "Breed 8", "Breed 9", "Breed 10", "Breed 11", "Breed 12",
        "Breed 13", "Breed 14", "Breed 15", "Breed 16", "Breed 17", "Breed 18",
    ),
) {

    Column(modifier = modifier.padding(48.dp)) {
        Text(
            text = "Cats App",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        var searchText by rememberSaveable { mutableStateOf("") }

        //TODO: Remove black line on the bottom of the search bar
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
            placeholder = { Text("Search") },
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
                .border(
                    width = 0.dp,
                    color = Color.Transparent,
                    shape = RoundedCornerShape(50.dp)
                )
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
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(catBreeds) { breed ->
                BreedItem(
                    breed,
                    isFavorite = false,
                    onFavoriteClick = {


                    }
                )
            }
        }

    }
}

@Composable
fun BreedItem(
    breed: String,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Gray)
        ) {
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
        Text(breed)
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_4_XL)
@Composable
fun OverviewScreenPreview() {
    OverviewScreen()
}