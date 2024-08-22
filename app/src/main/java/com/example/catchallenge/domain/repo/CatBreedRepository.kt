package com.example.catchallenge.domain.repo

import com.example.catchallenge.data.local.CatBreedEntity
import com.example.catchallenge.domain.model.CatBreed
import kotlinx.coroutines.flow.Flow

interface CatBreedRepository {
    fun fetchAllCatBreedsFromRemote(): Flow<List<CatBreed>>
    suspend fun persistCatBreeds(catBreeds: List<CatBreed>)
    fun getAllCatBreedsFromLocalStorage(): Flow<List<CatBreedEntity>>
    //fun getSingleCatBreedById(breedId: String): Flow<CatBreedEntity>
    suspend fun getSingleCatBreedById(breedId: String): CatBreedEntity?

    suspend fun updateFavoriteStatus(breedId: String, isFavorite: Boolean)
    suspend fun getFavouriteCatBreeds(): Flow<List<CatBreed>>
    //TODO: Operations regarding favourites
}