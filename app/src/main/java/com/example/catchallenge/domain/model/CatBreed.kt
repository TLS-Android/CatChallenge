package com.example.catchallenge.domain.model

import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

@TypeConverters(CatBreedImageDataTypeConverter::class)
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

    @TypeConverters(CatBreedImageDataTypeConverter::class)
    val image: CatBreedImageData?,

    val isFavourite: Boolean = false
)

@TypeConverters(CatBreedImageDataTypeConverter::class)
data class CatBreedImageData(
    @SerializedName("img_id")
    val imageId: String? = "",

    @SerializedName("url")
    val url: String? = ""
)


