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

    fun toggleFavorite(breed: CatBreed) {
        viewModelScope.launch {
            val updatedCatBreed = breed.copy(isFavourite = !breed.isFavourite)
            repository.updateCatBreed(updatedCatBreed)
            _uiState.value = uiState.value.copy(catBreed = updatedCatBreed)
            Log.d(
                "DetailViewModel",
                "Ui State Value Favourite: ${_uiState.value.catBreed?.isFavourite}"
            )
        }
    }

}

data class CatBreedDetailState(
    var catBreed: CatBreed? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)