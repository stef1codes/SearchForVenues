package com.example.androidassesmenttest.usecases.usecases

import com.example.androidassesmenttest.data.local.Entity.Relations.VenueDetailxCategoriesxIcon

interface GetVenueDetailFromRemoteUseCase {
    suspend operator fun invoke(id: String): List<VenueDetailxCategoriesxIcon>
}
