package com.example.androidassesmenttest.usecases.usecasesImpl

import com.example.androidassesmenttest.data.local.Entity.Relations.VenueDetailxCategoriesxIcon
import com.example.androidassesmenttest.repository.VenuesRepository
import com.example.androidassesmenttest.usecases.usecases.GetVenueDetailFromRemoteUseCase

class GetVenueDetailFromRemoteUseCaseImpl(private val repositoryImpl: VenuesRepository) :
    GetVenueDetailFromRemoteUseCase {

    override suspend fun invoke(id: String): List<VenueDetailxCategoriesxIcon> {
        return repositoryImpl.getVenueDetailRemote(id)
    }
}
