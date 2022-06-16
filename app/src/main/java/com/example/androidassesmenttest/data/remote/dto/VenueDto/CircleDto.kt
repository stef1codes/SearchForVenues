package com.example.androidassesmenttest.data.remote.dto.VenueDto

import com.google.gson.annotations.SerializedName

data class CircleDto(
    @SerializedName("center")
    val centerDto: CenterDto,
    @SerializedName("radius")
    val radius: Int
)
