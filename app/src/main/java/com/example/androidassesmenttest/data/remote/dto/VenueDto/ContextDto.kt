package com.example.androidassesmenttest.data.remote.dto.VenueDto

import com.google.gson.annotations.SerializedName

data class ContextDto(
    @SerializedName("geo_bounds")
    val geoBoundsDto: GeoBoundsDto
)
