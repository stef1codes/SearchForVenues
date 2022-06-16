package com.example.androidassesmenttest.pressentation.searchVenues.state

import com.example.androidassesmenttest.data.local.Entity.VenueEntity

sealed class VenuesState {

    abstract val venues: List<VenueEntity>?

    object Empty : VenuesState() {
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

