package com.example.androidassesmenttest.data.remote

import com.example.androidassesmenttest.data.remote.dto.VenueDetailDto.VenueDetailDto
import com.example.androidassesmenttest.data.remote.dto.VenueDto.VenueDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FourSquareApi {
    @GET("search")
    suspend fun getVenues(
        @Query("near") near: String,
        @Query("radius") radius: String,
        @Query("limit") limit: String
    ): VenueDto

    @GET("{fsq_id}")
    suspend fun getVenueDetail(@Path("fsq_id") venueId: String): VenueDetailDto
}
