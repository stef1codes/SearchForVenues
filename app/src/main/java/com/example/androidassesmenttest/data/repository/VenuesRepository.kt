package com.example.androidassesmenttest.data.repository

import com.example.androidassesmenttest.data.local.Entity.Relations.VenueDetailxCategoriesxIcon
import com.example.androidassesmenttest.data.local.Entity.VenueEntity

interface VenuesRepository {
    suspend fun getVenuesFromRemote(near: String, radius: String, limit: String): List<VenueEntity>
    suspend fun getVenueDetailFromRemote(id: String): List<VenueDetailxCategoriesxIcon>
    suspend fun getVenueDetailFromDatabase(id: String): List<VenueDetailxCategoriesxIcon>
    suspend fun getVenuesFromDatabase(): List<VenueEntity>
}
