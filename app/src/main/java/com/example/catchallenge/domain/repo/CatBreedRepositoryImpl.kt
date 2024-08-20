package com.example.catchallenge.domain.repo

import com.example.catchallenge.data.local.CatBreedDao
import com.example.catchallenge.domain.model.CatBreed
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CatBreedRepositoryImpl @Inject constructor(
    private val catBreedDao: CatBreedDao
): CatBreedRepository {

    override fun fetchAllCatBreeds(): Flow<List<CatBreed>> {
        TODO("Not yet implemented")
    }

    override fun searchCatBreeds(query: String): Flow<List<CatBreed>> {
        TODO("Not yet implemented")
    }

    override fun updateFavoriteStatus(breedName: String, favorite: Boolean) {
        TODO("Not yet implemented")
    }
}