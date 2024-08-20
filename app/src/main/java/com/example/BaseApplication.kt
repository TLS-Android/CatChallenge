package com.example

import android.app.Application
import androidx.room.Room
import com.example.catchallenge.data.local.CatDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {
    val database: CatDatabase by lazy {
        Room.databaseBuilder(
            this,
            CatDatabase::class.java,
            "cat_database"
        ).build()
    }
}