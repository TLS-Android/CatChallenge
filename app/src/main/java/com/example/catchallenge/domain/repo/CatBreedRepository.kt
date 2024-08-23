package com.example.catchallenge.domain.repo

import com.example.catchallenge.data.local.CatBreedEntity
import com.example.catchallenge.domain.model.CatBreed
import kotlinx.coroutines.flow.Flow

interface CatBreedRepository {
    fun fetchAllCatBreedsFromRemote(): Flow<List<CatBreed>>
    fun getAllCatBreedsFromLocalStorage(): Flow<List<CatBreedEntity>>
    suspend fun getSingleCatBreedById(breedId: String): CatBreedEntity?
    suspend fun updateFavoriteStatus(breedId: String, isFavorite: Boolean)
    suspend fun getFavouriteCatBreeds(): Flow<List<CatBreedEntity>>


    suspend fun persistCatBreeds(catBreeds: List<CatBreed>)
    suspend fun updateCatBreed(updatedCatBreed: CatBreed)
    //TODO: Operations regarding favourites
}