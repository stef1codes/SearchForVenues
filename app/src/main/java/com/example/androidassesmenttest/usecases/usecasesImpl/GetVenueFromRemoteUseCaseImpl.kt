package com.example.androidassesmenttest.usecases.usecasesImpl

import com.example.androidassesmenttest.data.local.Entity.VenueEntity
import com.example.androidassesmenttest.repository.VenuesRepository
import com.example.androidassesmenttest.usecases.usecases.GetVenuesFromRemoteUsecase

class GetVenueFromRemoteUseCaseImpl(private val venuesRepository: VenuesRepository) : GetVenuesFromRemoteUsecase {
    override suspend fun invoke(
        near: String,
        radius: Int?,
        limit: Int?,
    ): List<VenueEntity> {
        return venuesRepository.getVenuesRemote(near, radius, limit)
    }
}
