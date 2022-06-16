package com.example.androidassesmenttest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidassesmenttest.data.local.Entity.*

@Database(entities = [
    VenueEntity::class,
    VenueDetailEntity::class,
    CategoryEntity::class,
    LocationEntity::class
], version = 16, exportSchema = false)
abstract class VenueDatabase : RoomDatabase() {
    abstract val venueDao: VenueDao
}
