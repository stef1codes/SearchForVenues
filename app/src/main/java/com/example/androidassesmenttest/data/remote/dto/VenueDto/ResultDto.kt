package com.example.androidassesmenttest.data.remote.dto.VenueDto

import com.google.gson.annotations.SerializedName

data class ResultDto(
    @SerializedName("categories")
    val categoryDtos: List<CategoryDto>,
    @SerializedName("chains")
    val chains: List<Any>,
    @SerializedName("distance")
    val distance: Int?,
    @SerializedName("fsq_id")
    val fsqId: String,
    @SerializedName("geocodes")
    val geocodesDto: GeocodesDto,
    @SerializedName("link")
    val link: String,
    @SerializedName("location")
    val locationDto: LocationDto,
    @SerializedName("name")
    val name: String,
    @SerializedName("related_places")
    val relatedPlacesDto: RelatedPlacesDto,
    @SerializedName("timezone")
    val timezone: String
)
