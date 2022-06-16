package com.example.androidassesmenttest.usecases.usecasesImpl

import com.example.androidassesmenttest.data.local.Entity.LocationEntity
import com.example.androidassesmenttest.data.local.Entity.VenueDetailEntity
import com.example.androidassesmenttest.data.remote.dto.VenueDetailDto.VenueDetailDto
import com.example.androidassesmenttest.usecases.usecases.MapVenueDetailDtoToEntity

class MapVenueDetailDtoToEntityImpl : MapVenueDetailDtoToEntity {
    override fun invoke(dto: VenueDetailDto): VenueDetailEntity {
        return VenueDetailEntity(
            venueDetailId = dto.fsqId,
            link = dto.link,
            name = dto.name,
            location = LocationEntity(
                address = dto.locationDto.address,
                censusBlock = dto.locationDto.censusBlock,
                country = dto.locationDto.country,
                crossStreet = dto.locationDto.crossStreet,
                dma = dto.locationDto.dma,
                formattedAddress = dto.locationDto.formattedAddress,
                locality = dto.locationDto.locality,
                postcode = dto.locationDto.postcode,
                region = dto.locationDto.region)
        )
    }
}