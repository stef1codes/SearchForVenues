package com.example.androidassesmenttest.usecases.usecasesImpl

import androidx.room.withTransaction
import com.example.androidassesmenttest.data.local.Entity.VenueEntity
import com.example.androidassesmenttest.data.local.VenueDatabase
import com.example.androidassesmenttest.usecases.usecases.InsertVenueDataIntoDatabase

class InsertVenueDataIntoDatabaseImpl(private val db: VenueDatabase) : InsertVenueDataIntoDatabase {
    override suspend fun invoke(listVenue: List<VenueEntity>) {
        db.withTransaction {
            db.venueDao.deleteAllFromVenues()
            db.venueDao.insertVenues(listVenue)
        }
    }
}
