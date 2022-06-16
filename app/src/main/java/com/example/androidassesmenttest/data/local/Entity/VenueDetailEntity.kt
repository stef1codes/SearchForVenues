package com.example.androidassesmenttest.data.local.Entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Target(allowedTargets = [AnnotationTarget.FIELD, AnnotationTarget.FUNCTION])
@Retention(value = AnnotationRetention.BINARY)
annotation class Embedded
@Entity(tableName = "venue_detail_table")
data class VenueDetailEntity(
    @PrimaryKey
    val venueDetailId: String = "",
    val link: String? = "",
    val name: String? = "",
    @Embedded val location: LocationEntity? = null,
)