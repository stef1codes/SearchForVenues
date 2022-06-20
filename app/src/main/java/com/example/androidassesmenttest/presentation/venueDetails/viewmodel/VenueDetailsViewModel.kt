package com.example.androidassesmenttest.presentation.venueDetails.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidassesmenttest.presentation.venueDetails.state.VenueDetailState
import com.example.androidassesmenttest.usecases.usecases.GetVenueDetailFromRemoteUseCase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.*

class VenueDetailsViewModel(
    private val getVenueDetailFromRemoteUseCase: GetVenueDetailFromRemoteUseCase,
) : ViewModel() {
    private var _hasInternet = mutableStateOf<Boolean?>(null)
    private var _venueDetailsstate = MutableStateFlow<VenueDetailState>(VenueDetailState.Empty)
    private var _fsqId = mutableStateOf("")
    val hasInternet = _hasInternet
    val venueDetailsstate = _venueDetailsstate
    val fsqId = _fsqId.value

    fun getVenueDetail() = flow<VenueDetailState> {
        getVenueDetailFromRemoteUseCase(fsqId).let {
            _venueDetailsstate.emit(
                VenueDetailState.Success(
                    it.first().venueDetail,
                    it.first().category
                )
            )
        }
    }.flowOn(IO)
        .onStart { _venueDetailsstate.emit(VenueDetailState.Loading) }
        .catch { _venueDetailsstate.emit(VenueDetailState.Error(it.localizedMessage)) }
        .launchIn(viewModelScope)

    fun changeInternetState(state: Boolean?) {
        if (state == true) {
            getVenueDetail()
        }
        _hasInternet.value = state ?: false
    }

    fun setVenueDetailId(fsqid: String?) {
        _fsqId.value = fsqid ?: ""
    }
}
