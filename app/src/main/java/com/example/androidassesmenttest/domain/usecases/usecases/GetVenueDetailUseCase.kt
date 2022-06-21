package com.example.androidassesmenttest.domain.usecases.usecases

import com.example.androidassesmenttest.data.local.Entity.Relations.VenueDetailxCategoriesxIcon

interface GetVenueDetailUseCase {
    suspend operator fun invoke(id: String): List<VenueDetailxCategoriesxIcon>
}
