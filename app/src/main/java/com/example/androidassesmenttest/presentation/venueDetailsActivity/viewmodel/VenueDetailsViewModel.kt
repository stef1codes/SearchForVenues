package com.example.androidassesmenttest.presentation.venueDetailsActivity.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidassesmenttest.domain.usecases.usecases.GetVenueDetailUseCase
import com.example.androidassesmenttest.presentation.venueDetailsActivity.state.VenueDetailState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.*

class VenueDetailsViewModel(
    private val getVenueDetailUseCase: GetVenueDetailUseCase,
) : ViewModel() {
    private var mutableVenueDetailsState = MutableStateFlow<VenueDetailState>(VenueDetailState.Empty)
    val venueDetailsState: StateFlow<VenueDetailState> = mutableVenueDetailsState

    private var mutableVenueDetailsId = mutableStateOf("")
    private val venueDetailsId: State<String> = mutableVenueDetailsId

    fun getVenueDetail() = flow<VenueDetailState> {
        getVenueDetailUseCase(venueDetailsId.value).let {
            mutableVenueDetailsState.emit(
                VenueDetailState.Success(
                    it.first().venueDetail,
                    it.first().category
                )
            )
        }
    }.flowOn(IO)
        .onStart { mutableVenueDetailsState.emit(VenueDetailState.Loading) }
        .catch { mutableVenueDetailsState.emit(VenueDetailState.Error(it.localizedMessage)) }
        .launchIn(viewModelScope)

    fun SetvenueDetailsId(Id: String) {
        mutableVenueDetailsId.value = Id
    }
}
