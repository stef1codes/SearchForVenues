package com.example.androidassesmenttest.domain.usecases.usecasesImpl

import com.example.androidassesmenttest.data.local.Entity.VenueEntity
import com.example.androidassesmenttest.data.repository.VenuesRepository
import com.example.androidassesmenttest.domain.usecases.usecases.GetVenuesUseCase

class GetVenuesUseCaseImpl(
    private val venuesRepository: VenuesRepository,
    private val connectionObservation: ConnectionObservation,
) : GetVenuesUseCase {
    override suspend fun invoke(
        near: String,
        radius: String,
        limit: String,
    ): List<VenueEntity> {
        return if (connectionObservation.value == true)
            venuesRepository.getVenuesFromRemote(near, radius, limit)
        else {
            (venuesRepository.getVenuesFromDatabase())
        }
    }
}
