package com.example.androidassesmenttest.usecases.usecases

import com.example.androidassesmenttest.data.local.Entity.VenueEntity

interface GetVenuesFromRemoteUsecase {
    suspend operator fun invoke(
        near: String,
        radius: Int?,
        limit: Int?,
    ): List<VenueEntity>
}
