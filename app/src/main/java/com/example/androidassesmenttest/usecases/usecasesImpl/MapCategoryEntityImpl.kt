package com.example.androidassesmenttest.usecases.usecasesImpl

import com.example.androidassesmenttest.data.local.Entity.CategoryEntity
import com.example.androidassesmenttest.data.remote.dto.VenueDetailDto.VenueDetailDto
import com.example.androidassesmenttest.usecases.usecases.MapCategoryEntityUsecase

class MapCategoryEntityImpl : MapCategoryEntityUsecase {
    override fun invoke(dto: VenueDetailDto): List<CategoryEntity> {
        return dto.categories.map {
            CategoryEntity(
                categoryId = it.id,
                venueDetailId = dto.fsqId,
                name = it.name,
                prefix = it.iconDto.prefix,
                suffix = it.iconDto.prefix
            )
        }
    }
}