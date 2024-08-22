package com.example.catchallenge.ui.screens.detail

import android.util.Log
import androidx.activity.result.launch
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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: CatBreedRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CatBreedDetailState())
    val uiState: StateFlow<CatBreedDetailState> = _uiState.asStateFlow()

    private val _selectedCatBreed = MutableStateFlow<CatBreed?>(null)
    val selectedCatBreed: StateFlow<CatBreed?> = _selectedCatBreed.asStateFlow()

    //private var selectedCatBreed : CatBreed? = null

    private var newBreedId : String = ""

    init {
        savedStateHandle.get<String>("breedId")?.let { breedId ->
            newBreedId = breedId
            Log.d("DetailViewModel", "breedId: $breedId")
            viewModelScope.launch {
                repository.getSingleCatBreedById(breedId)?.let { catBreedEntity ->
                    _uiState.value = _uiState.value.copy(
                        catBreed = catBreedEntity.toCatBreed()
                    )
                }
            }
        }
    }


    private fun updateUiState() {
        _uiState.value = CatBreedDetailState(
            catBreed = _selectedCatBreed.value,
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
    var catBreed: CatBreed? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)