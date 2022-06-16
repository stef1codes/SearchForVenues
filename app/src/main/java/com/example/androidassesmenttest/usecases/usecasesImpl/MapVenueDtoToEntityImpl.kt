package com.example.androidassesmenttest.usecases.usecasesImpl

import com.example.androidassesmenttest.data.local.Entity.LocationEntity
import com.example.androidassesmenttest.data.local.Entity.VenueEntity
import com.example.androidassesmenttest.data.remote.dto.VenueDto.ResultDto
import com.example.androidassesmenttest.usecases.usecases.MapVenueDtoToEntity

class MapVenueDtoToEntityImpl : MapVenueDtoToEntity {
    override fun invoke(dto: List<ResultDto>): List<VenueEntity> {
        return dto.map {
            VenueEntity(
                fsqId = it.fsqId,
                distance = it.distance,
                name = it.name,
                location = LocationEntity(
                    address = it.locationDto.address,
                    censusBlock = it.locationDto.censusBlock,
                    country = it.locationDto.country,
                    crossStreet = it.locationDto.crossStreet,
                    formattedAddress = it.locationDto.formattedAddress,
                    locality = it.locationDto.locality,
                    postcode = it.locationDto.postcode,
                    region = it.locationDto.region)
            )
        }
    }
}