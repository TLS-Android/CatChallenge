package com.example.catchallenge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CatBreedEntity::class], version = 1)
abstract class CatBreedDatabase : RoomDatabase() {
    abstract fun catBreedDao(): CatBreedDao
}