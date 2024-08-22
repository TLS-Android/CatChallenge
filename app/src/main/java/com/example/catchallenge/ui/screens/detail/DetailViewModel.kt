package com.example.catchallenge.ui.screens.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catchallenge.domain.model.CatBreed
import com.example.catchallenge.domain.model.CatBreedImageData
import com.example.catchallenge.domain.repo.CatBreedRepository
import com.example.catchallenge.domain.repo.toCatBreed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: CatBreedRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CatBreedDetailState())
    val uiState: StateFlow<CatBreedDetailState> = _uiState.asStateFlow()

    private var selectedCatBreed : CatBreed? = null

    init {
        savedStateHandle.get<String>("breedId")?.let { breedId ->
            Log.d("DetailViewModel", "breedId: $breedId")
            viewModelScope.launch {
                repository.getSingleCatBreedById(breedId)
                    .collect { catBreedEntity ->
                        Log.d("DetailViewModel", "catBreedEntity: ${catBreedEntity.toCatBreed()}")

                        /*
                            _uiState.value = _uiState.value.copy(
                                catBreed = catBreedEntity.toCatBreed()
                            )
                        */

                    }
            }
            //updateUiState()
        }
    }

    private fun updateUiState() {
        _uiState.value = CatBreedDetailState(
            catBreed = selectedCatBreed,
            isLoading = false,
            error = null
        )

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
            image = CatBreedImageData(
                imageId = "123",
                url = "https://cdn2.thecatapi.com/images/OGTWqNNOt.jpg"
            ),
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