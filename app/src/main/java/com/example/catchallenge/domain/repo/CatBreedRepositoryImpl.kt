package com.example.catchallenge.domain.repo

import com.example.catchallenge.data.local.CatBreedDao
import com.example.catchallenge.data.local.CatBreedEntity
import com.example.catchallenge.domain.model.CatBreed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CatBreedRepositoryImpl @Inject constructor(
    private val catBreedDao: CatBreedDao
): CatBreedRepository {

    override fun fetchAllCatBreeds(): Flow<List<CatBreed>> = flow {
        emit(catBreedDao.fetchAllCatBreeds().first().map { it.toCatBreed() })
    }

    override fun searchCatBreeds(query: String): Flow<List<CatBreed>> {
        TODO("Not yet implemented")
    }

    override fun getCatBreedById(catBreedId: String): Flow<CatBreed> {
        TODO("Not yet implemented")
    }

    override suspend fun updateFavoriteStatus(breedName: String) {
        TODO("Not yet implemented")
    }
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