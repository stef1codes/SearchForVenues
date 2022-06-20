package com.example.androidassesmenttest.usecases.usecases

import com.example.androidassesmenttest.data.local.Entity.VenueEntity

interface InsertVenueDataIntoDatabase {
    suspend operator fun invoke(listVenue: List<VenueEntity>)
}
