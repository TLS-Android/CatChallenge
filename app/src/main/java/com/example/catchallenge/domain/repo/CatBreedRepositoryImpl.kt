package com.example.catchallenge.domain.repo

import com.example.catchallenge.data.local.CatBreedDao
import com.example.catchallenge.domain.model.CatBreed
import kotlinx.coroutines.flow.Flow

class CatBreedRepositoryImpl(private val catBreedDao: CatBreedDao) {
    //val catBreeds: Flow<List<CatBreed>> = catBreedDao.fetchAllCatBreeds()

    /*
    suspend fun updateFavoriteStatus(breedName: String, isFavorite: Boolean) {
        catBreedDao.updateFavoriteStatus(breedName, isFavorite)
    }
    */
}