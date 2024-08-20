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

    private val _uiState = MutableStateFlow(OverviewState())
    val uiState: StateFlow<OverviewState> = _uiState.asStateFlow()

    init {
        getFavouriteCatBreeds()
    }

    private fun getFavouriteCatBreeds() {
        //TODO()
    }
}

data class OverviewState(
    val catBreeds: List<CatBreed> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)
