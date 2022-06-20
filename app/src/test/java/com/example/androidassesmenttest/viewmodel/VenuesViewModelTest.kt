package com.example.androidassesmenttest.viewmodel

import android.util.Log
import com.example.androidassesmenttest.data.local.Entity.VenueEntity
import com.example.androidassesmenttest.presentation.searchVenues.state.VenuesState
import com.example.androidassesmenttest.usecases.usecases.GetVenuesFromRemoteUsecase
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class VenuesViewModelTest {
    private val getVenuesFromRemoteUsecase = mockk<GetVenuesFromRemoteUsecase>(relaxed = true)

    private lateinit var venuesViewModel: VenuesViewModel
    val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `Test if venueState is correct type when input is valid`() = runTest {
        venuesViewModel = VenuesViewModel(getVenuesFromRemoteUsecase)
        venuesViewModel.setNearQuery("rotterdam")
        venuesViewModel.setRadiusQuery("10000")
        venuesViewModel.setLimitQuery("10000")
        venuesViewModel.getVenues()
        assertThat(venuesViewModel.venueState.value).isInstanceOf(VenuesState.Success::class.java)
        assertThat(venuesViewModel.venueState.value.venues?.isNotEmpty())
    }

    @Test
    fun `Test if venueState is correct type when no requests has been made`() = runTest {
        venuesViewModel = VenuesViewModel(getVenuesFromRemoteUsecase)
        assertThat(venuesViewModel.venueState.value).isInstanceOf(VenuesState.Empty::class.java)
        assertThat(venuesViewModel.venueState.value.venues?.isEmpty())
    }

    @Test
    fun `Test if venueState is correct type when input is invalid`() = runTest {
        venuesViewModel = VenuesViewModel(getVenuesFromRemoteUsecase)
         venuesViewModel.setNearQuery("'@#$%")
         venuesViewModel.setRadiusQuery("0")
         venuesViewModel.getVenues()
        assertThat(venuesViewModel.venueState.value.venues?.isNotEmpty())

    }


    @Test
    fun `Test valid input for Radius & Limit`() {
        venuesViewModel = VenuesViewModel(getVenuesFromRemoteUsecase)

        venuesViewModel.setRadiusQuery("1000")
        venuesViewModel.setLimitQuery("50")
        assertEquals("1000", venuesViewModel.radius.value)
        assertEquals("50", venuesViewModel.limit.value)
    }

    @Test
    fun `Test if function filters out input of type string`() {
        venuesViewModel = VenuesViewModel(getVenuesFromRemoteUsecase)

        venuesViewModel.setRadiusQuery("1a2b3c")
        venuesViewModel.setLimitQuery("1a2b3c")
        assertEquals("123", venuesViewModel.radius.value)
        assertEquals("123", venuesViewModel.limit.value)
    }

    @Test
    fun `Test Internet State when null`() {
        venuesViewModel = VenuesViewModel(getVenuesFromRemoteUsecase)

        venuesViewModel.changeInternetState(null)
        assertEquals(false, venuesViewModel.hasInternet.value)
    }

    @Test
    fun `Test Internet State when false`() {
        venuesViewModel = VenuesViewModel(getVenuesFromRemoteUsecase)

        venuesViewModel.changeInternetState(false)
        assertEquals(false, venuesViewModel.hasInternet.value)
    }

    @Test
    fun `Test Internet State when true`() = runTest {
        venuesViewModel = VenuesViewModel(getVenuesFromRemoteUsecase)

        venuesViewModel.changeInternetState(true)
        assertEquals(true, venuesViewModel.hasInternet.value)
    }
}
