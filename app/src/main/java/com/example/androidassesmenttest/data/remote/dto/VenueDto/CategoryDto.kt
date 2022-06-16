package com.example.androidassesmenttest.data.remote.dto.VenueDto

import com.google.gson.annotations.SerializedName

data class CategoryDto(
    @SerializedName("icon")
    val iconDto: IconDto,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)
