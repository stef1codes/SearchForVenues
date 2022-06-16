package com.example.androidassesmenttest.data.remote.dto.VenueDetailDto

import com.google.gson.annotations.SerializedName

data class VenueDetailDto(
    @SerializedName("categories")
    val categories: List<CategoryDto>,
    @SerializedName("chains")
    val chains: List<Any>,
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
