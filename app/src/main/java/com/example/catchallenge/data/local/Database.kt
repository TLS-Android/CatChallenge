package com.example.catchallenge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.catchallenge.domain.model.CatBreed

@Database(entities = [CatBreed::class], version = 1)
abstract class CatDatabase : RoomDatabase() {
    abstract fun catBreedDao(): CatBreedDao
}