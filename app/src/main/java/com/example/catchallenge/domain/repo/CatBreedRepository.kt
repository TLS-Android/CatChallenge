package com.example.catchallenge.domain.repo

import com.example.catchallenge.domain.model.CatBreed
import kotlinx.coroutines.flow.Flow

interface CatBreedRepository {
    fun fetchAllCatBreeds(): Flow<List<CatBreed>>
    fun searchCatBreeds(query: String): Flow<List<CatBreed>>
    fun updateFavoriteStatus(breedName: String, favorite: Boolean)
    //TODO: Operations regarding favourites
}