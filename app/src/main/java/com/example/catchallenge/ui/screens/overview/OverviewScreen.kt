package com.example.catchallenge.ui.screens.overview

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.catchallenge.domain.model.CatBreed
import com.example.catchallenge.ui.navigation.Screen

@Composable
fun OverviewScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    catBreeds: List<CatBreed> = listOf(
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
        CatBreed("11", "Breed 11"),
        CatBreed("12", "Breed 12"),
        CatBreed("13", "Breed 13"),
        CatBreed("14", "Breed 14"),
        CatBreed("15", "Breed 15"),
        CatBreed("16", "Breed 16"),
        CatBreed("17", "Breed 17"),
        CatBreed("18", "Breed 18")
    )
) {
    Column(modifier = modifier.padding(48.dp)) {
        Text(
            text = "Cats App",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        var searchText by rememberSaveable { mutableStateOf("") }

        val filteredBreeds = if (searchText.isBlank()) {
            catBreeds
        } else {
            catBreeds.filter { it.name.contains(searchText, ignoreCase = true) }
        }

        TextField(
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            value = searchText,
            onValueChange = { searchText = it },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
            placeholder = { Text("Search") },
            shape = RoundedCornerShape(50.dp),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
                .border(
                    width = 0.dp,
                    color = Color.Transparent,
                    shape = RoundedCornerShape(50.dp)
                )
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
                items(filteredBreeds) { breed ->
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

@Composable
fun CatBreedItem(
    breed: CatBreed,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    navController: NavController
) {
    var isFav by remember { mutableStateOf(isFavorite) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Gray)
                .clickable {
                    navController.navigate(Screen.Detail.route + "/${breed.id}")
                }
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
        Text(breed.name)
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_4_XL)
@Composable
fun OverviewScreenPreview() {
    OverviewScreen(
        navController = rememberNavController()
    )
}