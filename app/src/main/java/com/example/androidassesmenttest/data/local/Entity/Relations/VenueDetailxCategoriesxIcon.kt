package com.example.androidassesmenttest.data.local.Entity.Relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.androidassesmenttest.data.local.Entity.CategoryEntity
import com.example.androidassesmenttest.data.local.Entity.VenueDetailEntity

data class VenueDetailxCategoriesxIcon(
    @Embedded
    val venueDetail: VenueDetailEntity,

    @Relation(
        entity = CategoryEntity::class,
        parentColumn = "venueDetailId",
        entityColumn = "venueDetailId",
    )
    val category: List<CategoryEntity>,

)
