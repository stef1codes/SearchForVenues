package com.example.androidassesmenttest.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidassesmenttest.presentation.searchVenues.state.VenuesState
import com.example.androidassesmenttest.usecases.usecases.GetVenuesFromRemoteUsecase
import kotlinx.coroutines.flow.*

class VenuesViewModel(
    private val getVenuesFromRemoteUseCase: GetVenuesFromRemoteUsecase,
) : ViewModel() {
    var near = mutableStateOf<String?>(null)
    private var _limit = mutableStateOf<String?>(null)
    private var _radius = mutableStateOf<String?>( null)
    private var _hasInternet = mutableStateOf<Boolean?>(null)
    private var _venuesstate = MutableStateFlow<VenuesState>(VenuesState.Empty)
    var limit = _limit
    var radius = _radius
    var hasInternet = _hasInternet
    val venueState = _venuesstate

    fun getVenues() = flow<VenuesState> {
        getVenuesFromRemoteUseCase.invoke(
            near = near.value?:"",
            radius = radius.value?.toInt(),
            limit = limit.value?.toInt()
        )
            .sortedBy { it.distance }
            .let { _venuesstate.emit(VenuesState.Success(it)) }
    }.onStart { _venuesstate.emit(VenuesState.Loading) }
        .catch { _venuesstate.emit(VenuesState.Error(it.message)) }
        .launchIn(viewModelScope)

    fun setNearQuery(it: String) { near.value = it }

    fun setRadiusQuery(newRadius: String) = checkUserInput(newRadius, _radius)

    fun setLimitQuery(newLimit: String): Unit = checkUserInput(newLimit, _limit)


    private fun checkIfInputAreDigits(input: String): Boolean = input.all { Character.isDigit(it) }

    private fun checkUserInput(input: String, value: MutableState<String?>) {

        val filteredInput = input.filter { checkIfInputAreDigits(it.toString()) }
        if(filteredInput == "" && value.value?.length!! <=1){
            value.value = null
        }else{
            value.value = filteredInput
        }
    }

    fun changeInternetState(state: Boolean?) {
        if (state == true) {
            _hasInternet.value = true
            //getVenues()
        }
        _hasInternet.value = state ?: false
    }
}
