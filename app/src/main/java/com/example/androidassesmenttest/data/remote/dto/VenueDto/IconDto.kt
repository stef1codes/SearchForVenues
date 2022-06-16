package com.example.androidassesmenttest.data.remote.dto.VenueDto

import com.google.gson.annotations.SerializedName

data class IconDto(
    @SerializedName("prefix")
    val prefix: String,
    @SerializedName("suffix")
    val suffix: String
)
