package com.example.androidassesmenttest.data.remote.dto.VenueDto

import com.google.gson.annotations.SerializedName

data class LocationDto(
    @SerializedName("address")
    val address: String,
    @SerializedName("census_block")
    val censusBlock: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("cross_street")
    val crossStreet: String,
    @SerializedName("formatted_address")
    val formattedAddress: String,
    @SerializedName("locality")
    val locality: String,
    @SerializedName("neighborhood")
    val neighborhood: List<String>,
    @SerializedName("postcode")
    val postcode: String,
    @SerializedName("region")
    val region: String
)
