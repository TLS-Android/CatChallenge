package com.example.catchallenge.ui.screens.overview

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.catchallenge.data.local.CatBreedDao
import com.example.catchallenge.data.local.SharedPreferenceHelper
import com.example.catchallenge.domain.model.CatBreed
import com.example.catchallenge.domain.model.CatBreedImageData
import com.example.catchallenge.domain.repo.CatBreedRepository
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OverviewViewModelTest {

    private val sharedPreferenceHelper: SharedPreferenceHelper = mockk()
    private val catBreedRepository: CatBreedRepository = mockk()
    private lateinit var viewModel: OverviewViewModel

    private val mockCatBreedImageData = CatBreedImageData(
        imageId = "123",
        url = "https://cdn2.thecatapi.com/images/OGTWqNNOt.jpg"
    )

    val catBreedsMock: List<CatBreed> = listOf(
        CatBreed("1", "Breed 1", image = mockCatBreedImageData),
        CatBreed("2", "Breed 2", image = mockCatBreedImageData),
    )


    @Test
    fun testInitialDataFetch_noLocalData() {
        every { sharedPreferenceHelper.hasFetchedInitialData() } returns false
        every { catBreedRepository.fetchAllCatBreedsFromRemote() } returns flowOf(catBreedsMock)
        coEvery { catBreedRepository.persistCatBreeds(any()) } just Runs

        viewModel = OverviewViewModel(
            catBreedRepository,
            sharedPreferenceHelper
        )

        val uiState = viewModel.uiState.value
        Assert.assertEquals(catBreedsMock, uiState.catBreeds)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testToggleFavorite() = runTest {
        val catBreedDao = mockk<CatBreedDao>()

        coEvery { catBreedRepository.updateFavoriteStatus(any(), any()) } just Runs
        coEvery { catBreedDao.updateFavoriteStatus(any(), any()) } just Runs

        viewModel = OverviewViewModel(catBreedRepository, sharedPreferenceHelper)
        viewModel.setInitialCatBreeds(catBreedsMock)

        val breedToToggle = catBreedsMock[0]
        viewModel.toggleFavorite(breedToToggle)

        advanceUntilIdle()

        coVerify { catBreedDao.updateFavoriteStatus(breedToToggle.id, true) }
    }


}