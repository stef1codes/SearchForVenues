package com.example.androidassesmenttest.usecases.usecasesImpl

import androidx.room.withTransaction
import com.example.androidassesmenttest.data.local.Entity.CategoryEntity
import com.example.androidassesmenttest.data.local.Entity.VenueDetailEntity
import com.example.androidassesmenttest.data.local.VenueDatabase
import com.example.androidassesmenttest.usecases.usecases.InsertVenueDetailDataIntoDatabase

class InsertVenueDetailDataIntoDatabaseImpl(private val db: VenueDatabase) :
    InsertVenueDetailDataIntoDatabase {

    override suspend fun invoke(
        venueDetail: VenueDetailEntity,
        mappedCategories: List<CategoryEntity>,
    ) {
        db.withTransaction {
            db.venueDao.deleteAllFromVenueDetail()
            db.venueDao.deleteAllFromCategories()
            db.venueDao.insertVenueDetail(venueDetail)
            db.venueDao.insertCategories(mappedCategories)
        }
    }
}
