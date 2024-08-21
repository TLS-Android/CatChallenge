package com.example.catchallenge.domain.repo

import com.example.catchallenge.domain.model.CatBreed
import kotlinx.coroutines.flow.Flow

interface CatBreedRepository {
    fun fetchAllCatBreeds(): Flow<List<CatBreed>>
    fun searchCatBreeds(query: String): Flow<List<CatBreed>>
    fun getCatBreedById(catBreedId: String): Flow<CatBreed>
    suspend fun updateFavoriteStatus(breedId: String, isFavorite: Boolean)
    suspend fun getFavouriteCatBreeds(): Flow<List<CatBreed>>
    //TODO: Operations regarding favourites
}