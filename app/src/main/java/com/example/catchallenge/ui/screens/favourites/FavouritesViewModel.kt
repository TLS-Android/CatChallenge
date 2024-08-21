package com.example.catchallenge.ui.screens.favourites

import androidx.lifecycle.ViewModel
import com.example.catchallenge.domain.model.CatBreed
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

    private val favCatBreeds: List<CatBreed> = listOf(
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

    init {
        getFavouriteCatBreeds()
        updateUiState()
    }

    private fun updateUiState() {
        _uiState.value = FavoritesUIState(
            favoriteCatBreeds = favCatBreeds,
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
