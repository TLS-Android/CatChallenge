package com.example.catchallenge.ui.screens.overview

import com.example.catchallenge.domain.model.CatBreed
import com.example.catchallenge.domain.repo.CatBreedRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock

class OverviewViewModelTest {

    private lateinit var viewModel: OverviewViewModel
    private lateinit var repository: CatBreedRepository

    @Test
    fun `getCatBreed - success`() = runBlocking {

        val expectedCatBreed = CatBreed(
            id = "1",
            name = "Siamese",
            origin = "Thailand",
            temperament = "Affectionate, social, playful, and intelligent",
            description = "The Siamese cat is one of the first distinctly " +
                    "recognized breeds of Asian cat.",
        )

        `when`(repository.getCatBreedById("1")).thenReturn(expectedCatBreed)

        viewModel.getCatBreed("1")

        Assert.assertEquals(expectedCatBreed, viewModel.uiState.value.catBreeds)
        Assert.assertFalse(viewModel.uiState.value.isLoading)
        Assert.assertNull(viewModel.uiState.value.error)
    }

    @Before
    fun setUp() {
        repository = mock()
        viewModel = OverviewViewModel(repository)
    }

    @Test
    fun getUiState() {
    }

    @Test
    fun toggleFavorite() {
    }

    @Test
    fun getBreedById() {
    }
}