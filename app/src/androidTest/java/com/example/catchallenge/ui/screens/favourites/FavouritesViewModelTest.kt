package com.example.catchallenge.ui.screens.favourites

import com.example.catchallenge.domain.repo.CatBreedRepository

import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock

class FavouritesViewModelTest {

    private lateinit var viewModel: FavouritesViewModel
    private lateinit var repository: CatBreedRepository

    @Before
    fun setUp() {
        repository = mock()
        viewModel = FavouritesViewModel(repository)

    }

    @Test
    fun getUiState() {
    }
}