package com.example.androidassesmenttest.pressentation.venueDetails.state

import com.example.androidassesmenttest.data.local.Entity.CategoryEntity
import com.example.androidassesmenttest.data.local.Entity.VenueDetailEntity

sealed class VenueDetailState {


    object Empty : VenueDetailState()



    object Loading : VenueDetailState()


    data class Error(
        val errorMessage: String?,
    ) : VenueDetailState()


    data class Success(
        val venueDetail: VenueDetailEntity = VenueDetailEntity(),
        val category: List<CategoryEntity> = emptyList(),

        ) : VenueDetailState()
}
