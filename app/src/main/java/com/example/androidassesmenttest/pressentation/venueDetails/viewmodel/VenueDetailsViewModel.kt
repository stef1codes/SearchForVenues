package com.example.androidassesmenttest.pressentation.venueDetails.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidassesmenttest.pressentation.venueDetails.state.VenueDetailState
import com.example.androidassesmenttest.usecases.usecases.GetVenueDetailFromRemoteUseCase
import com.example.androidassesmenttest.usecases.usecases.GetVenueDetailWithCategoriesUsecase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.*

class VenueDetailsViewModel(
    private val getVenueDetailFromRemoteUseCase: GetVenueDetailFromRemoteUseCase,
):ViewModel() {
    private var _hasInternet = mutableStateOf<Boolean?>(null)
    var hasInternet = _hasInternet

    private var _venueDetailsstate = MutableStateFlow<VenueDetailState>(VenueDetailState.Empty)
    val venueDetailsstate: StateFlow<VenueDetailState> = _venueDetailsstate

    private var _fsqId  = mutableStateOf("")
    var fsqId: String = _fsqId.value

     fun getVenueDetail() = flow<VenueDetailState> {

        getVenueDetailFromRemoteUseCase(fsqId).let {
            _venueDetailsstate.emit(VenueDetailState.Success(it.first().venueDetail,it.first().category))
        }

    }.flowOn(IO)
        .onStart { _venueDetailsstate.emit(VenueDetailState.Loading) }
        .catch {
            Log.v("stefan",it.localizedMessage?:"")
            _venueDetailsstate.emit(VenueDetailState.Error(it.localizedMessage)) }
        .launchIn(viewModelScope)



    fun changeInternetState(state: Boolean?) {
        if(state == true){
            getVenueDetail()
        }
        _hasInternet.value = state ?: false
    }

    fun setVenueDetailId(fsqid: String?) {
        fsqId = fsqid?:""
    }
}