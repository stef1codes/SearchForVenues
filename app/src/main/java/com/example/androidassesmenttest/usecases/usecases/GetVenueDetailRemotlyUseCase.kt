package com.example.androidassesmenttest.usecases.usecases

import com.example.androidassesmenttest.data.local.Entity.VenueDetailEntity

interface GetVenueDetailRemotlyUseCase {
    suspend operator fun invoke(
        id: String
    ): VenueDetailEntity
}
