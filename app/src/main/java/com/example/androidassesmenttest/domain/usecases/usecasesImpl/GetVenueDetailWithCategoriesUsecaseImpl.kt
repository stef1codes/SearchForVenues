package com.example.androidassesmenttest.domain.usecases.usecasesImpl

import com.example.androidassesmenttest.data.local.Entity.Relations.VenueDetailxCategoriesxIcon
import com.example.androidassesmenttest.data.repository.VenuesRepository
import com.example.androidassesmenttest.domain.usecases.usecases.GetVenueDetailWithCategoriesUsecase

class GetVenueDetailWithCategoriesUsecaseImpl(private val repository: VenuesRepository) : GetVenueDetailWithCategoriesUsecase {
    override suspend fun invoke(id: String): List<VenueDetailxCategoriesxIcon> {
        return repository.getVenueDetailfromDatabase(id)
    }
}
