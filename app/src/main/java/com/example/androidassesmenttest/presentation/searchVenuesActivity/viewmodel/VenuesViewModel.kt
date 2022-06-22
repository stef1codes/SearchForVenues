package com.example.androidassesmenttest.presentation.searchVenuesActivity.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidassesmenttest.domain.usecases.usecases.GetVenuesUseCase
import com.example.androidassesmenttest.presentation.searchVenuesActivity.state.VenuesState
import com.example.androidassesmenttest.util.Constants.EMPTYLIST_MSG
import kotlinx.coroutines.flow.*

class VenuesViewModel(
    private val getVenuesUseCase: GetVenuesUseCase,
) : ViewModel() {
    private var mutableVenueStateFlow = MutableStateFlow<VenuesState>(VenuesState.Empty())
    val venueState: MutableStateFlow<VenuesState> = mutableVenueStateFlow

    fun getVenues(
        near: String,
        limit: String,
        radius: String,
    ) = flow<VenuesState> {
        getVenuesUseCase.invoke(
            near = near,
            radius = radius,
            limit = limit,
        )
            .sortedBy { it.distance }
            .let {
                if (it.isNullOrEmpty()) mutableVenueStateFlow.emit(VenuesState.Empty(EMPTYLIST_MSG))
                else mutableVenueStateFlow.emit(VenuesState.Success(it))
            }
    }
        .onStart { mutableVenueStateFlow.emit(VenuesState.Loading) }
        .catch { mutableVenueStateFlow.emit(VenuesState.Error(it.localizedMessage)) }
        .launchIn(viewModelScope)
}
