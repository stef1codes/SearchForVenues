package com.example.androidassesmenttest.venueDao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.androidassesmenttest.data.local.Entity.LocationEntity
import com.example.androidassesmenttest.data.local.Entity.VenueEntity
import com.example.androidassesmenttest.data.local.VenueDao
import com.example.androidassesmenttest.data.local.VenueDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class VenueDaoTest {

    private lateinit var venueDao: VenueDao
    private lateinit var db: VenueDatabase

    val venue = listOf(
        VenueEntity(
            distance = 10,
            name = "amsterdam",
            location = LocationEntity(
                address = "sesamstraat",
                censusBlock = "censusBlock",
                country = "NL",
                crossStreet = "crossStreet",
                dma = "dma",
                formattedAddress = "formattedAddress",
                locality = "locality",
                postcode = "1234GR",
                region = "region"
            ),
        )
    )

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, VenueDatabase::class.java
        ).build()
        venueDao = db.venueDao
    }

    @Test
    @Throws(Exception::class)
    fun writeVenueAndReadInList() = runTest {
        venueDao.insertVenues(venue)
        val byName: List<VenueEntity> = venueDao.getVenues().first()
        assertEquals(1, byName.size)
    }

    @Test
    @Throws(Exception::class)
    fun writeVenueAndDeleteInList() = runTest {
        venueDao.insertVenues(venue)
        venueDao.deleteAllFromVenues()
        assertEquals(0, venueDao.getVenues().first().size)
    }

    @After
    @Throws(IOException::class)
    fun destroy() {
        db.close()
    }
}
