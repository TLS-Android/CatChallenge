package com.example.catchallenge.ui.screens.favourites

import androidx.lifecycle.ViewModel
import com.example.catchallenge.domain.model.CatBreed
import com.example.catchallenge.domain.model.CatBreedImageData
import com.example.catchallenge.domain.repo.CatBreedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val catBreedRepository: CatBreedRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoritesUIState())
    val uiState: StateFlow<FavoritesUIState> = _uiState.asStateFlow()


    private val mockCatBreedImageData = CatBreedImageData(
        imageId = "123",
        url = "https://cdn2.thecatapi.com/images/OGTWqNNOt.jpg"
    )

    private val favCatBreeds: List<CatBreed> = listOf(
        CatBreed("1", "Breed 1", image = mockCatBreedImageData),
        CatBreed("2", "Breed 2", image = mockCatBreedImageData),
        CatBreed("3", "Breed 3", image = mockCatBreedImageData),
        CatBreed("4", "Breed 4", image = mockCatBreedImageData),
        CatBreed("5", "Breed 5", image = mockCatBreedImageData),
        CatBreed("6", "Breed 6", image = mockCatBreedImageData),
        CatBreed("7", "Breed 7", image = mockCatBreedImageData),
        CatBreed("8", "Breed 8", image = mockCatBreedImageData),
        CatBreed("9", "Breed 9", image = mockCatBreedImageData),
        CatBreed("10", "Breed 10", image = mockCatBreedImageData),
    )

    init {
        getFavouriteCatBreeds()
        updateUiState()
    }

    private fun updateUiState() {
        _uiState.value = FavoritesUIState(
            favoriteCatBreeds = uiState.value.favoriteCatBreeds,
            searchQuery = "",
            isLoading = false,
            error = null
        )
    }

    private fun getFavouriteCatBreeds() {
        //TODO()
    }
}

data class FavoritesUIState(
    val favoriteCatBreeds: List<CatBreed> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)
