package com.example.androidassesmenttest.data.remote.dto.VenueDetailDto

import com.google.gson.annotations.SerializedName

data class RoofDto(
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)
