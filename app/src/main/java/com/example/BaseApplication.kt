package com.example

import android.app.Application
import androidx.room.Room
import com.example.catchallenge.data.local.CatBreedDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {
    val database: CatBreedDatabase by lazy {
        Room.databaseBuilder(
            this,
            CatBreedDatabase::class.java,
            "cat_database"
        ).build()
    }
}