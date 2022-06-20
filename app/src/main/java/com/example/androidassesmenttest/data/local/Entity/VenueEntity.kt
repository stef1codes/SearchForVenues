package com.example.androidassesmenttest.data.local.Entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "venue_table")
data class VenueEntity(
    @PrimaryKey @ColumnInfo("fsq_id")
    val fsqId: String = "",
    val distance: Int? = null,
    val name: String? = "",
    @Embedded val location: LocationEntity? = LocationEntity(),
)
