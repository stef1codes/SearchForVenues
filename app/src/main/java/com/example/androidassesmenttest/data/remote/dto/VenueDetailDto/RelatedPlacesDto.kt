package com.example.androidassesmenttest.data.remote.dto.VenueDetailDto

import com.google.gson.annotations.SerializedName

data class RelatedPlacesDto(
    @SerializedName("children")
    val children: List<ChildrenDto>
)
