package com.example.catchallenge.domain.model

import com.google.gson.annotations.SerializedName
import dagger.Provides

data class CatBreedsFullResponse(
    val breeds: List<CatBreed>,
)

data class CatBreedResponse(
    val breeds: CatBreed,
    val id: String,
    val url: String,
    val width: Int,
    val height: Int
)

data class CatBreed(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("origin")
    val origin: String? = "",

    @SerializedName("temperament")
    val temperament: String? = "",

    @SerializedName("description")
    val description: String? = "",

    @SerializedName("imageUrl")
    val imageUrl: String? = "",

    val isFavourite: Boolean = false
)
