package com.example.androidassesmenttest.domain.usecases.usecases

import com.example.androidassesmenttest.data.local.Entity.VenueDetailEntity
import com.example.androidassesmenttest.data.remote.dto.VenueDetailDto.VenueDetailDto

interface MapVenueDetailDtoToEntity {
    operator fun invoke(dto: VenueDetailDto): VenueDetailEntity
}
