package com.example.catchallenge.data.remote

import com.example.catchallenge.domain.model.CatBreed
import retrofit2.http.GET

interface CatBreedApi {

    @GET("breeds")
    suspend fun getCatBreeds(): List<CatBreed>
}