package com.example.androidassesmenttest.data.remote.dto.VenueDto

import com.google.gson.annotations.SerializedName

data class GeoBoundsDto(
    @SerializedName("circle")
    val circleDto: CircleDto
)
