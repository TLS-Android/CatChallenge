package com.example.catchallenge.data.remote

import com.example.catchallenge.domain.model.CatBreed
import retrofit2.http.GET

interface CatBreedService {

    @GET("breeds")
    suspend fun getCatBreeds(): List<CatBreed>

    @GET("breeds/search")
    suspend fun searchCatBreeds(query: String): List<CatBreed>

}