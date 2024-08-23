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
            isLoading = false,
            error = null
        )
    }

    private fun getFavouriteCatBreeds() {
        viewModelScope.launch {
            try {
                catBreedRepository.getFavouriteCatBreeds()
                    .map { catBreedEntities ->
                        catBreedEntities.map { it.toCatBreed() }
                    }
                    .collect { catBreeds ->
                        _uiState.value = uiState.value.copy(
                            favoriteCatBreeds = catBreeds
                        )
                    }
            } catch (e: Exception) {
                _uiState.value = uiState.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }

        }
    }

    fun toggleFavorite(breed: CatBreed) {
        viewModelScope.launch {
            try {
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

                Log.d("OverviewViewModel", "Ui State Value Favourite: " +
                        "${_uiState.value.favoriteCatBreeds.find { it.id == breed.id }?.isFavourite}"
                )
            } catch (e: Exception) {
                _uiState.value = uiState.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }

        }
    }
}

data class FavoritesUIState(
    val favoriteCatBreeds: List<CatBreed> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
