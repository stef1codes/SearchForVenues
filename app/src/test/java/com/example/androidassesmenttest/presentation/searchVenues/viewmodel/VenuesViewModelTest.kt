package com.example.androidassesmenttest.presentation.searchVenues.viewmodel

import com.example.androidassesmenttest.domain.usecases.usecases.GetVenuesUseCase
import com.example.androidassesmenttest.presentation.searchVenuesActivity.state.VenuesState
import com.example.androidassesmenttest.presentation.searchVenuesActivity.viewmodel.VenuesViewModel
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.concurrent.CountDownLatch

@OptIn(ExperimentalCoroutinesApi::class)
class VenuesViewModelTest {
    private val getVenuesFromRemoteUsecase = mockk<GetVenuesUseCase>()

    private lateinit var venuesViewModel: VenuesViewModel
    private val dispatcher = StandardTestDispatcher()
    private val lock: CountDownLatch = CountDownLatch(2)

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Test if venueState is correct type when input is valid`() = runBlocking {
        venuesViewModel = VenuesViewModel(getVenuesFromRemoteUsecase)
        venuesViewModel.getVenues("", "", "")
        assertThat(venuesViewModel.venueState.value).isInstanceOf(VenuesState.Success::class.java)
    }

    @Test
    fun `Test if venueState is correct type when no requests has been made`() = runTest {
        venuesViewModel = VenuesViewModel(getVenuesFromRemoteUsecase)
        assertThat(venuesViewModel.venueState.value).isInstanceOf(VenuesState.Empty::class.java,)
        assertThat(venuesViewModel.venueState.value.venues?.isEmpty())
    }

    @Test
    fun `Test if venueState is correct type when having an exception`() = runTest {
        venuesViewModel = VenuesViewModel(getVenuesFromRemoteUsecase)
        venuesViewModel.getVenues("this should give an error", "-1", "-100")
        assertThat(venuesViewModel.venueState.value).isInstanceOf(VenuesState.Error::class.java)
    }
}
