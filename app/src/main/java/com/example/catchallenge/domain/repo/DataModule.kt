package com.example.catchallenge.domain.repo

import android.app.Application
import androidx.room.Room
import com.example.catchallenge.data.local.CatBreedDao
import com.example.catchallenge.data.local.CatDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindCatBreedRepository(impl: CatBreedRepositoryImpl): CatBreedRepository
}


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCatDatabase(app: Application): CatDatabase {
        return Room.databaseBuilder(app, CatDatabase::class.java, "cat_database")
            .build()
    }

    @Provides
    fun provideCatBreedDao(database: CatDatabase): CatBreedDao {
        return database.catBreedDao()
    }
}