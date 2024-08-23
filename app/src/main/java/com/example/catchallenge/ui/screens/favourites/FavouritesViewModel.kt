package com.example.catchallenge.ui.screens.favourites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catchallenge.domain.model.CatBreed
import com.example.catchallenge.domain.repo.CatBreedRepository
import com.example.catchallenge.domain.repo.toCatBreed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val catBreedRepository: CatBreedRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoritesUIState())
    val uiState: StateFlow<FavoritesUIState> = _uiState.asStateFlow()

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
        viewModelScope.launch {
            catBreedRepository.getFavouriteCatBreeds()
                .map { catBreedEntities ->
                    catBreedEntities.map { it.toCatBreed() }
                }
                .collect { catBreeds ->
                    _uiState.value = uiState.value.copy(
                        favoriteCatBreeds = catBreeds
                    )
                }
        }
    }

    fun toggleFavorite(breed: CatBreed) {
        viewModelScope.launch {
            val updatedIsFavorite = !breed.isFavourite
            catBreedRepository.updateFavoriteStatus(breed.id, updatedIsFavorite)

            val updatedCatBreeds = uiState.value.favoriteCatBreeds.map {
                if (it.id == breed.id) {
                    it.copy(isFavourite = updatedIsFavorite)
                } else {
                    it
                }
            }

            _uiState.value = uiState.value.copy(favoriteCatBreeds = updatedCatBreeds)

            Log.d(
                "OverviewViewModel",
                "Ui State Value Favourite: " +
                        "${_uiState.value.favoriteCatBreeds.find { it.id == breed.id }?.isFavourite}"
            )
        }
    }
}

data class FavoritesUIState(
    val favoriteCatBreeds: List<CatBreed> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)
