package com.example.androidassesmenttest.usecases.usecases

import com.example.androidassesmenttest.data.local.Entity.CategoryEntity
import com.example.androidassesmenttest.data.remote.dto.VenueDetailDto.VenueDetailDto

interface MapCategoryEntityUsecase {
    operator fun invoke(dto: VenueDetailDto): List<CategoryEntity>
}
