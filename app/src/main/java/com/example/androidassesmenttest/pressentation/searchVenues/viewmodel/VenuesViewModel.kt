package com.example.androidassesmenttest.pressentation.searchVenues.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidassesmenttest.pressentation.searchVenues.state.VenuesState
import com.example.androidassesmenttest.pressentation.venueDetails.state.VenueDetailState
import com.example.androidassesmenttest.usecases.usecases.GetVenueDetailFromRemoteUseCase
import com.example.androidassesmenttest.usecases.usecases.GetVenuesFromRemoteUsecase
import kotlinx.coroutines.flow.*


class VenuesViewModel(
    private val getVenuesFromRemoteUseCase: GetVenuesFromRemoteUsecase,
    private val getVenueDetailFromRemoteUseCase: GetVenueDetailFromRemoteUseCase,
) : ViewModel() {
    var _near = mutableStateOf("")
    var near = _near

    var _limit = mutableStateOf<Int?>(null)
    var limit = _limit

    var _radius = mutableStateOf<Int?>(null)
    var radius = _radius

    private var _hasInternet = mutableStateOf<Boolean?>(null)
    var hasInternet = _hasInternet

    private var _venuesstate = MutableStateFlow<VenuesState>(VenuesState.Empty)
    val venuestate = _venuesstate


    fun getVenues() = flow<VenuesState> {
            getVenuesFromRemoteUseCase.invoke(
                near = near.value,
                radius = radius.value,
                limit = limit.value)
                .sortedBy { it.distance }
                .let { _venuesstate.emit(VenuesState.Success(it)) }
    }.onStart { _venuesstate.emit(VenuesState.Loading) }
        .catch { _venuesstate.emit(VenuesState.Error(it.cause?.message?:"couldn't find a venue near by")) }
        .launchIn(viewModelScope)

    fun updateNearText(it: String) {
        near.value = it
    }

    fun updateLimitText(newLimit: String) = checkUserInput(newLimit, _limit)

    fun updateRadiusText(newRadius: String) = checkUserInput(newRadius, _radius)

    private fun checkIfInputAreDigits(input: String): Boolean = input.all { Character.isDigit(it) }

    private fun checkUserInput(input: String, value: MutableState<Int?>) {
        if (checkIfInputAreDigits(input) && input.length < 8 && input.isNotEmpty()) {
            value.value = input.toInt()
        } else if (input.isEmpty()) {
            value.value = null
        }
    }

    fun changeInternetState(state: Boolean?) {
        if(state == true){
            getVenues()
        }
        _hasInternet.value = state ?: false
    }


}



