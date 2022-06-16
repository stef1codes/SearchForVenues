package com.example.androidassesmenttest.data.local.Entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(indices = [Index(value = ["locationId"])])
data class LocationEntity(
    @PrimaryKey val locationId: String = UUID.randomUUID().toString(),
    var address: String? = "",
    var censusBlock: String? = "",
    var country: String? = "",
    var crossStreet: String? = "",
    var dma: String? = "",
    var formattedAddress: String? = "",
    var locality: String? = "",
    var postcode: String? = "",
    var region: String? = "",
)