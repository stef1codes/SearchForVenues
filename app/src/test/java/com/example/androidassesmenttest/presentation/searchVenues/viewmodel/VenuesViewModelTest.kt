package com.example.androidassesmenttest.presentation.searchVenues.viewmodel

import com.example.androidassesmenttest.MainDispatcherRule
import com.example.androidassesmenttest.data.local.Entity.VenueEntity
import com.example.androidassesmenttest.domain.usecases.usecases.GetVenuesUseCase
import com.example.androidassesmenttest.presentation.searchVenuesActivity.state.VenuesState
import com.example.androidassesmenttest.presentation.searchVenuesActivity.viewmodel.VenuesViewModel
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.*

@OptIn(ExperimentalCoroutinesApi::class)
class VenuesViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainDispatcherRule()

    private val getVenuesFromRemoteUseCaseMockk = mockk<GetVenuesUseCase>()

    private lateinit var venuesViewModel: VenuesViewModel

    @Test
    fun `When GetVenuesUseCase returns a list, venueState should be of type Success`() = runTest {
        coEvery { getVenuesFromRemoteUseCaseMockk.invoke("", "", "") } returns listOf(
            VenueEntity()
        )
        venuesViewModel = VenuesViewModel(getVenuesFromRemoteUseCaseMockk)
        venuesViewModel.getVenues("", "", "")

        assertThat(venuesViewModel.venueState.value).isInstanceOf(VenuesState.Success::class.java)
    }

    @Test
    fun `When GetVenuesUseCase is not called, venueState should be of type Empty`() = runTest {
        venuesViewModel = VenuesViewModel(getVenuesFromRemoteUseCaseMockk)
        assertThat(venuesViewModel.venueState.value).isInstanceOf(VenuesState.Empty::class.java)
    }

    @Test
    fun `When GetVenuesUseCase is called but return emptylist, venueState should be of type Empty`() = runTest {
        coEvery { getVenuesFromRemoteUseCaseMockk.invoke("", "", "") } returns emptyList()
        venuesViewModel = VenuesViewModel(getVenuesFromRemoteUseCaseMockk)
        assertThat(venuesViewModel.venueState.value).isInstanceOf(VenuesState.Empty::class.java)
    }

    @Test
    fun `When GetVenuesUseCase couldn't retrieve data, venueState should be of type  Error`() = runTest() {
        coEvery { getVenuesFromRemoteUseCaseMockk.invoke("", "", "") } throws Throwable("")

        venuesViewModel = VenuesViewModel(getVenuesFromRemoteUseCaseMockk)
        venuesViewModel.getVenues("this should give an error", "-1", "-100")
        assertThat(venuesViewModel.venueState.value).isInstanceOf(VenuesState.Error::class.java)
    }

    @Test
    fun `When GetVenuesUseCase couldn't retrieve data, venueState should be of type Loading`() = runTest(dispatchTimeoutMs = 10000) {
        coEvery { getVenuesFromRemoteUseCaseMockk.invoke("", "", "") } returns listOf(
            VenueEntity()
        )
        venuesViewModel = VenuesViewModel(getVenuesFromRemoteUseCaseMockk)
        venuesViewModel.getVenues("", "", "")

        assertThat(venuesViewModel.venueState.value).isInstanceOf(VenuesState.Success::class.java)
    }
}
