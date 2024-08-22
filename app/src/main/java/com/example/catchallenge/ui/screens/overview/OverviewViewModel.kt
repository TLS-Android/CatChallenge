package com.example.catchallenge.ui.screens.overview

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catchallenge.domain.model.CatBreed
import com.example.catchallenge.domain.repo.CatBreedRepository
import com.example.catchallenge.ui.screens.detail.CatBreedDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val catBreedRepository: CatBreedRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(OverviewState())
    val uiState: StateFlow<OverviewState> = _uiState.asStateFlow()

    private val _catBreeds = MutableStateFlow<List<CatBreed>>(emptyList())
    val catBreeds: StateFlow<List<CatBreed>> = _catBreeds.asStateFlow()

    private val catBreedsMock: List<CatBreed> = listOf(
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

    init {
        viewModelScope.launch {
            catBreedRepository.fetchAllCatBreedsFromRemote().collect { breeds ->
                _uiState.value = _uiState.value.copy(catBreeds = breeds)

                Log.d("OverviewViewModel", "Breeds: $breeds")
            }
        }

        updateUiState()
    }

    private fun updateUiState() {
        _uiState.value = OverviewState(
            catBreeds = uiState.value.catBreeds,
            searchQuery = "",
            isLoading = false,
            error = null
        )

    }

    fun updateFavoriteStatus(breedName: String, isFavorite: Boolean) {
        viewModelScope.launch {
            catBreedRepository.updateFavoriteStatus(breedName, isFavorite)
        }
    }

}

data class OverviewState(
    var catBreeds: List<CatBreed> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

