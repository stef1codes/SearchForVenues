package com.example.androidassesmenttest.data.remote.dto.VenueDto

import com.google.gson.annotations.SerializedName

data class RoofDto(
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)
