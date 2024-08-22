package com.example.catchallenge.domain.model

import androidx.room.TypeConverter
import com.google.gson.Gson

class CatBreedImageDataTypeConverter {
    @TypeConverter
    fun fromCatBreedImageData(imageData: CatBreedImageData?): String? {
        return Gson().toJson(imageData)
    }

    @TypeConverter
    fun toCatBreedImageData(data: String?): CatBreedImageData? {
        return Gson().fromJson(data, CatBreedImageData::class.java)
    }
}