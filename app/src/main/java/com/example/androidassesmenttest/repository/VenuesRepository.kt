package com.example.androidassesmenttest.repository

import com.example.androidassesmenttest.data.local.Entity.Relations.VenueDetailxCategoriesxIcon
import com.example.androidassesmenttest.data.local.Entity.VenueEntity

interface VenuesRepository {
    suspend fun getVenuesRemote(near: String, radius: Int?, limit: Int?): List<VenueEntity>
    suspend fun getVenueDetailRemote(id: String): List<VenueDetailxCategoriesxIcon>
    suspend fun getVenueWithCategories(id: String): List<VenueDetailxCategoriesxIcon>
}
