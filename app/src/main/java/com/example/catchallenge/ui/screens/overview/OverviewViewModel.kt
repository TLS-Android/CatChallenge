package com.example.catchallenge.ui.screens.overview

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catchallenge.data.local.SharedPreferenceHelper
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
class OverviewViewModel @Inject constructor(
    private val catBreedRepository: CatBreedRepository,
    private val sharedPreferenceHelper: SharedPreferenceHelper
) : ViewModel() {

    private val _uiState = MutableStateFlow(OverviewState())
    val uiState: StateFlow<OverviewState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                if (!sharedPreferenceHelper.hasFetchedInitialData()) {
                    Log.d("OverviewViewModel", "No local data. Fetching from remote.")
                    catBreedRepository.fetchAllCatBreedsFromRemote().collect { breeds ->
                        _uiState.value = _uiState.value.copy(
                            catBreeds = breeds,
                            isLoading = false
                        )
                        catBreedRepository.persistCatBreeds(breeds)
                        sharedPreferenceHelper.setFetchedInitialData(true)
                        Log.d("OverviewViewModel", "Breeds from remote: $breeds")
                    }
                } else {
                    Log.d("OverviewViewModel", "Local data available. Using local data.")
                    catBreedRepository.getAllCatBreedsFromLocalStorage().collect { breeds ->
                        val transformedBreeds = breeds.map {
                            it.toCatBreed()
                        }
                        _uiState.value = _uiState.value.copy(
                            catBreeds = transformedBreeds,
                            isLoading = false
                        )
                        Log.d("OverviewViewModel", "Breeds from local: $breeds")
                    }
                }

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }

        }
    }


    fun toggleFavorite(breed: CatBreed) {
        viewModelScope.launch {
            val updatedIsFavorite = !breed.isFavourite
            catBreedRepository.updateFavoriteStatus(breed.id, updatedIsFavorite)

            val updatedCatBreeds = uiState.value.catBreeds.map {
                if (it.id == breed.id) {
                    it.copy(isFavourite = updatedIsFavorite)
                } else {
                    it
                }
            }

            _uiState.value = uiState.value.copy(catBreeds = updatedCatBreeds)

            Log.d(
                "OverviewViewModel",
                "Ui State Value Favourite: " +
                    "${_uiState.value.catBreeds.find { it.id == breed.id }?.isFavourite}"
            )
        }
    }

    companion object {
        private val mockCatBreedImageData = CatBreedImageData(
            imageId = "123",
            url = "https://cdn2.thecatapi.com/images/OGTWqNNOt.jpg"
        )

        val catBreedsMock: List<CatBreed> = listOf(
            CatBreed("1", "Breed 1", image = mockCatBreedImageData),
            CatBreed("2", "Breed 2", image = mockCatBreedImageData,),
            CatBreed("3", "Breed 3", image = mockCatBreedImageData),
            CatBreed("4", "Breed 4", image = mockCatBreedImageData),
            CatBreed("5", "Breed 5", image = mockCatBreedImageData),
            CatBreed("6", "Breed 6", image = mockCatBreedImageData),
            CatBreed("7", "Breed 7", image = mockCatBreedImageData),
            CatBreed("8", "Breed 8", image = mockCatBreedImageData),
            CatBreed("9", "Breed 9", image = mockCatBreedImageData),
            CatBreed("10", "Breed 10", image = mockCatBreedImageData),
            CatBreed("11", "Breed 11", image = mockCatBreedImageData),
            CatBreed("12", "Breed 12", image = mockCatBreedImageData),
            CatBreed("13", "Breed 13", image = mockCatBreedImageData),
            CatBreed("14", "Breed 14", image = mockCatBreedImageData),
            CatBreed("15", "Breed 15", image = mockCatBreedImageData),
            CatBreed("16", "Breed 16", image = mockCatBreedImageData),
            CatBreed("17", "Breed 17", image = mockCatBreedImageData),
            CatBreed("18", "Breed 18", image = mockCatBreedImageData)
        )
    }

}

data class OverviewState(
    var catBreeds: List<CatBreed> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = true,
    val error: String? = null
)

