package com.example.androidassesmenttest.data.remote.dto.VenueDetailDto

import com.google.gson.annotations.SerializedName

data class ChildrenDto(
    @SerializedName("fsq_id")
    val fsqId: String,
    @SerializedName("name")
    val name: String
)
