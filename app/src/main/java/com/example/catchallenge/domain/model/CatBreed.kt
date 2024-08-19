package com.example.catchallenge.domain.model

data class CatBreed(
    val id: String,
    val name: String,
    val origin: String,
    val temperament: String,
    val description: String,
    val lifespan: String,
    val imageUrl: String?,
    val isFavourite: Boolean = false
)
