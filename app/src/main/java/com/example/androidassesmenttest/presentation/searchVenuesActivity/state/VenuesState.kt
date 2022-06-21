package com.example.androidassesmenttest.presentation.searchVenuesActivity.state

import com.example.androidassesmenttest.data.local.Entity.VenueEntity

sealed class VenuesState {

    abstract val venues: List<VenueEntity>?

    data class Empty(val message: String? = null) : VenuesState() {
        override val venues: List<VenueEntity>
            get() = emptyList()
    }

    object Loading : VenuesState() {
        override val venues: List<VenueEntity>
            get() = emptyList()
    }

    data class Error(val errorMessage: String?) : VenuesState() {
        override val venues: List<VenueEntity>
            get() = emptyList()
    }

    data class Success(
        override val venues: List<VenueEntity>? = emptyList(),
    ) : VenuesState()
}
