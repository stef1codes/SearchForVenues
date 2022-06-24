package com.example.androidassesmenttest.presentation.searchVenuesActivity.state

import com.example.androidassesmenttest.data.local.Entity.VenueEntity

sealed class VenuesState {

    data class Empty(val message: String = "") : VenuesState()

    object Loading : VenuesState()

    data class Error(val errorMessage: String = "") : VenuesState()

    data class Success(
        val venues: List<VenueEntity>? = emptyList(),
    ) : VenuesState()
}
