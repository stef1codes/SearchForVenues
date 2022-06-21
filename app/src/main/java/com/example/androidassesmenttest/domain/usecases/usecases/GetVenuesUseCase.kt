package com.example.androidassesmenttest.domain.usecases.usecases

import com.example.androidassesmenttest.data.local.Entity.VenueEntity

interface GetVenuesUseCase {
    suspend operator fun invoke(
        near: String,
        radius: String,
        limit: String,
    ): List<VenueEntity>
}
