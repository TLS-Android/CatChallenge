package com.example.catchallenge.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.catchallenge.domain.model.CatBreed
import com.example.catchallenge.domain.repo.CatBreedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val catBreedRepository: CatBreedRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(CatBreedDetailState())
    val uiState: StateFlow<CatBreedDetailState> = _uiState.asStateFlow()

    init {
        savedStateHandle.get<String>("breedId")?.let { breedId ->
            getCatBreed(breedId)
        }
    }

    private fun getCatBreed(breedId: String) {
        //TODO()
    }

}

data class CatBreedDetailState(
    val catBreed: CatBreed? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)