package com.example.androidassesmenttest.data.remote.dto.VenueDto

import com.google.gson.annotations.SerializedName

data class VenueDto(
    @SerializedName("context")
    val contextDto: ContextDto,
    @SerializedName("results")
    val resultDtos: List<ResultDto>
)

