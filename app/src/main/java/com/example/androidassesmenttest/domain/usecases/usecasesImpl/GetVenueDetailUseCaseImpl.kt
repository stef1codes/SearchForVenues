package com.example.androidassesmenttest.domain.usecases.usecasesImpl

import com.example.androidassesmenttest.data.local.Entity.Relations.VenueDetailxCategoriesxIcon
import com.example.androidassesmenttest.data.repository.VenuesRepository
import com.example.androidassesmenttest.domain.usecases.usecases.GetVenueDetailUseCase

class GetVenueDetailUseCaseImpl(
    private val repositoryImpl: VenuesRepository,
    private val connectionObservation: ConnectionObservation,
) :
    GetVenueDetailUseCase {

    override suspend fun invoke(id: String): List<VenueDetailxCategoriesxIcon> {
        return if (connectionObservation.value == true) {
            repositoryImpl.getVenueDetailFromRemote(id)
        } else {
            repositoryImpl.getVenueDetailfromDatabase(id)
        }
    }
}
