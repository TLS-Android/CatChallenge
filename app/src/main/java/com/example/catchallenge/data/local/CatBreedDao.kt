package com.example.catchallenge.data.local

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CatBreedDao {
    @Query("SELECT * FROM cat_breeds")
    fun fetchAllCatBreeds(): Flow<List<CatBreedEntity>>

    @Query("SELECT * FROM cat_breeds WHERE name LIKE '%' || :query || '%'")
    fun searchCatBreeds(query: String): Flow<List<CatBreedEntity>>
}