package com.example.androidassesmenttest.domain.usecases.usecases

import com.example.androidassesmenttest.data.local.Entity.CategoryEntity
import com.example.androidassesmenttest.data.local.Entity.VenueDetailEntity

interface InsertVenueDetailDataIntoDatabase {
    suspend operator fun invoke(
        venueDetail: VenueDetailEntity,
        mappedCategories: List<CategoryEntity>
    )
}
