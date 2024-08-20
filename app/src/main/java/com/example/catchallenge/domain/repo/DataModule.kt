package com.example.catchallenge.domain.repo

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun provideCatBreedRepository(impl: CatBreedRepositoryImpl): CatBreedRepository

}