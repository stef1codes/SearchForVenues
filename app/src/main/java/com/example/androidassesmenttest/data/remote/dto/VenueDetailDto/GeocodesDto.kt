package com.example.androidassesmenttest.data.remote.dto.VenueDetailDto

import com.google.gson.annotations.SerializedName

data class GeocodesDto(
    @SerializedName("main")
    val mainDto: MainDto,
    @SerializedName("roof")
    val roofDto: RoofDto
)
