package com.example.catchallenge.domain.repo

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.catchallenge.data.local.CatBreedDao
import com.example.catchallenge.data.local.CatBreedDatabase
import com.example.catchallenge.data.local.SharedPreferenceHelper
import com.example.catchallenge.data.remote.CatBreedService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideSharedPreferenceHelper(@ApplicationContext context: Context): SharedPreferenceHelper {
        return SharedPreferenceHelper(context)
    }

    @Provides
    @Singleton
    fun provideCatDatabase(app: Application): CatBreedDatabase {
        return Room.databaseBuilder(app, CatBreedDatabase::class.java, "cat_database")
            .build()
    }

    @Provides
    fun provideCatBreedDao(database: CatBreedDatabase): CatBreedDao {
        return database.catBreedDao()
    }

    @Provides
    fun provideCatBreedService(retrofit: Retrofit): CatBreedService {
        return retrofit.create(CatBreedService::class.java)
    }

    @Provides
    fun provideCatBreedRepository(
        catBreedService: CatBreedService,
        catBreedDao: CatBreedDao,
        database: CatBreedDatabase
    ): CatBreedRepository {
        return CatBreedRepositoryImpl(
            catBreedDao,
            catBreedService,
            database,
        )
    }

}