package com.example.catchallenge.domain.repo

import com.example.catchallenge.data.local.CatBreedDao
import com.example.catchallenge.data.local.CatBreedEntity
import com.example.catchallenge.data.local.CatBreedDatabase
import com.example.catchallenge.data.remote.CatBreedService
import com.example.catchallenge.domain.model.CatBreed
import com.example.catchallenge.domain.model.CatBreedImageData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CatBreedRepositoryImpl @Inject constructor(
    private val catBreedDao: CatBreedDao,
    private val service: CatBreedService,
    private val database: CatBreedDatabase,
): CatBreedRepository {

    override fun fetchAllCatBreedsFromRemote(): Flow<List<CatBreed>> = flow {
        val response = service.getCatBreeds()
        emit(response)
    }.flowOn(Dispatchers.IO)


    //region Favourites

    override fun searchCatBreeds(query: String): Flow<List<CatBreed>> {
        TODO("Not yet implemented")
    }

    override fun getCatBreedById(catBreedId: String): Flow<CatBreed> {
        TODO("Not yet implemented")
    }

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
        url = this.image?.url,
        imageId = this.image?.imageId,
        isFavourite = isFavourite
    )
}

fun CatBreedEntity.toCatBreed(): CatBreed {
    return CatBreed(
        id = id,
        name = name,
        temperament = temperament,
        description = description,
        image = CatBreedImageData(
            imageId = imageId,
            url = url
        ),
        isFavourite = isFavourite
    )
}