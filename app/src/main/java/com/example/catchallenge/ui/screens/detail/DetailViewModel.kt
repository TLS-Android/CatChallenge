package com.example.catchallenge.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catchallenge.domain.model.CatBreed
import com.example.catchallenge.domain.repo.CatBreedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: CatBreedRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CatBreedDetailState())
    val uiState: StateFlow<CatBreedDetailState> = _uiState.asStateFlow()

    init {
        savedStateHandle.get<String>("breedId")?.let { breedId ->
            //getCatBreed(breedId)
            getBreedById(breedId)
        }
    }

    private fun getCatBreed(breedId: String) {
        /*
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val catBreed: CatBreed? = repository.getCatBreedById(breedId).firstOrNull()
                _uiState.value = _uiState.value.copy(
                    catBreed = catBreed,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Error fetching breed details",
                    isLoading = false
                )
            }
        }
        */
    }


    //TODO: Add logic
    fun getBreedById(breedId: String): CatBreed {
        return CatBreed(
            id = "1",
            name = "Siamese",
            origin = "Thailand",
            temperament = "Affectionate, social, playful, and intelligent",
            description = "The Siamese cat is one of the first distinctly " +
                    "recognized breeds of Asian cat.",
            imageUrl = "https://cdn2.thecatapi.com/images/2v0.jpg",
        )
    }

    fun toggleFavorite(breed: CatBreed) {

    }

}

data class CatBreedDetailState(
    val catBreed: CatBreed? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)