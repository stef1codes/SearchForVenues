package com.example.androidassesmenttest.usecases.usecasesImpl

import com.example.androidassesmenttest.data.local.Entity.Relations.VenueDetailxCategoriesxIcon
import com.example.androidassesmenttest.repository.VenuesRepository
import com.example.androidassesmenttest.usecases.usecases.GetVenueDetailWithCategoriesUsecase

class GetVenueDetailWithCategoriesUsecaseImpl(private val repository: VenuesRepository) : GetVenueDetailWithCategoriesUsecase {
    override suspend fun invoke(id: String): List<VenueDetailxCategoriesxIcon> {
        return repository.getVenueWithCategories(id)
    }
}
