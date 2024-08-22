package com.example.catchallenge.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.catchallenge.domain.model.CatBreedImageDataTypeConverter

@Entity(tableName = "cat_breeds")
data class CatBreedEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val temperament: String?,
    val description: String?,
    val imageId: String?,
    val url: String?,
    val isFavourite: Boolean = false
)
