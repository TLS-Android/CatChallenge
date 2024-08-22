package com.example.catchallenge.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.catchallenge.domain.model.CatBreed
import kotlinx.coroutines.flow.Flow

@Dao
interface CatBreedDao {
    @Query("SELECT * FROM cat_breeds")
    fun fetchAllCatBreeds(): Flow<List<CatBreedEntity>>

    @Query("SELECT * FROM cat_breeds WHERE id = :breedId")
    suspend fun fetchSingleCatBreedById(breedId: String): CatBreedEntity?

    @Query("SELECT * FROM cat_breeds WHERE id LIKE '%' || :query || '%'")
    fun searchCatBreeds(query: String): Flow<List<CatBreedEntity>>

    @Query("SELECT * FROM cat_breeds WHERE isFavourite = 1")
    fun getFavoriteCatBreeds(): Flow<List<CatBreed>>

    @Query("UPDATE cat_breeds SET isFavourite = :isFavorite WHERE id = :breedId")
    suspend fun updateFavoriteStatus(breedId: String, isFavorite: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCatBreeds(catBreeds: List<CatBreedEntity>)

    @Update
    suspend fun updateCatBreed(catBreed: CatBreedEntity)
}