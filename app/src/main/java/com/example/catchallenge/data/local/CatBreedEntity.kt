package com.example.catchallenge.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cat_breeds")
data class CatBreedEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val origin: String?,
    val temperament: String?,
    val description: String?,
    val lifespan: String?,
    val imageUrl: String?,
    val isFavourite: Boolean = false
)
