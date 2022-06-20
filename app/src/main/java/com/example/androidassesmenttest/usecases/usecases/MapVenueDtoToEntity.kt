package com.example.androidassesmenttest.usecases.usecases

import com.example.androidassesmenttest.data.local.Entity.VenueEntity
import com.example.androidassesmenttest.data.remote.dto.VenueDto.ResultDto

interface MapVenueDtoToEntity {
    operator fun invoke(dto: List<ResultDto>): List<VenueEntity>
}
