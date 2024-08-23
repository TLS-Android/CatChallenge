package com.example.ui.screens.overview

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.catchallenge.data.local.SharedPreferenceHelper
import com.example.catchallenge.domain.model.CatBreed
import com.example.catchallenge.domain.model.CatBreedImageData
import com.example.catchallenge.domain.repo.CatBreedRepository
import com.example.catchallenge.ui.screens.overview.OverviewViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class OverviewViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var sharedPreferenceHelper: SharedPreferenceHelper

    @Inject
    lateinit var catBreedRepository: CatBreedRepository

    private lateinit var viewModel: OverviewViewModel

    private val mockCatBreedImageData = CatBreedImageData(
        imageId = "123",
        url = "https://cdn2.thecatapi.com/images/OGTWqNNOt.jpg"
    )

    val catBreedsMock: List<CatBreed> = listOf(
        CatBreed("1", "Breed 1", image = mockCatBreedImageData),
        CatBreed("2", "Breed 2", image = mockCatBreedImageData),
        CatBreed("3", "Breed 3", image = mockCatBreedImageData),
        CatBreed("4", "Breed 4", image = mockCatBreedImageData),
        CatBreed("5", "Breed 5", image = mockCatBreedImageData),
        CatBreed("6", "Breed 6", image = mockCatBreedImageData),
        CatBreed("7", "Breed 7", image = mockCatBreedImageData),
        CatBreed("8", "Breed 8", image = mockCatBreedImageData),
        CatBreed("9", "Breed 9", image = mockCatBreedImageData),
        CatBreed("10", "Breed 10", image = mockCatBreedImageData),
        CatBreed("11", "Breed 11", image = mockCatBreedImageData),
        CatBreed("12", "Breed 12", image = mockCatBreedImageData),
        CatBreed("13", "Breed 13", image = mockCatBreedImageData),
        CatBreed("14", "Breed 14", image = mockCatBreedImageData),
        CatBreed("15", "Breed 15", image = mockCatBreedImageData),
        CatBreed("16", "Breed 16", image = mockCatBreedImageData),
        CatBreed("17", "Breed 17", image = mockCatBreedImageData),
        CatBreed("18", "Breed 18", image = mockCatBreedImageData)
    )

    @Before
    fun setUp() {
        hiltRule.inject()
        viewModel = OverviewViewModel(catBreedRepository, sharedPreferenceHelper)
    }

    @Test
    fun testInitialDataFetch_noLocalData() = runTest {

        `when`(sharedPreferenceHelper.hasFetchedInitialData()).thenReturn(false)
        `when`(catBreedRepository.fetchAllCatBreedsFromRemote()).thenReturn(flowOf(catBreedsMock))

        val uiState = viewModel.uiState.value

        Assert.assertEquals(catBreedsMock, uiState.catBreeds)
        Assert.assertFalse(uiState.isLoading)
        verify(sharedPreferenceHelper).setFetchedInitialData(true)
    }
}