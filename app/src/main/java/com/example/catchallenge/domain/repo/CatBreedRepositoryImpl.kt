package com.example.catchallenge.domain.repo

import androidx.compose.foundation.text2.input.insert
import androidx.room.withTransaction
import com.example.catchallenge.data.local.CatBreedDao
import com.example.catchallenge.data.local.CatBreedEntity
import com.example.catchallenge.data.local.CatBreedDatabase
import com.example.catchallenge.data.remote.CatBreedService
import com.example.catchallenge.domain.model.CatBreed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CatBreedRepositoryImpl @Inject constructor(
    private val catBreedDao: CatBreedDao,
    private val api: CatBreedService,
    database: CatBreedDatabase,
): CatBreedRepository {

    //region Get Breeds
    override fun fetchAllCatBreeds(): Flow<List<CatBreed>> = flow {
        emit(catBreedDao.fetchAllCatBreeds().first().map { it.toCatBreed() })
    }


    override fun searchCatBreeds(query: String): Flow<List<CatBreed>> {
        TODO("Not yet implemented")
    }

    override fun getCatBreedById(catBreedId: String): Flow<CatBreed> {
        TODO("Not yet implemented")
    }

    //endregion


    //region Favourites
    override suspend fun updateFavoriteStatus(breedId: String, isFavorite: Boolean) {
        catBreedDao.updateFavoriteStatus(breedId, isFavorite)
    }

    override suspend fun getFavouriteCatBreeds(): Flow<List<CatBreed>> {
        return catBreedDao.getFavoriteCatBreeds()
    }
    //endregion


}

fun CatBreed.toCatBreedEntity(): CatBreedEntity {
    return CatBreedEntity(
        id = id,
        name = name,
        temperament = temperament,
        description = description,
        imageUrl = imageUrl,
        isFavourite = isFavourite
    )
}

fun CatBreedEntity.toCatBreed(): CatBreed {
    return CatBreed(
        id = id,
        name = name,
        temperament = temperament,
        description = description,
        imageUrl = imageUrl,
        isFavourite = isFavourite
    )
}