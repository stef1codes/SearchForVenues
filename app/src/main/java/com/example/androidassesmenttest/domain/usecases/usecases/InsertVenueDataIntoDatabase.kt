package com.example.androidassesmenttest.domain.usecases.usecases

import com.example.androidassesmenttest.data.local.Entity.VenueEntity

interface InsertVenueDataIntoDatabase {
    suspend operator fun invoke(listVenue: List<VenueEntity>)
}
