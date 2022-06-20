package com.example.androidassesmenttest.data.local.Entity

import androidx.room.*

@Entity("category_table")
data class CategoryEntity(
    @PrimaryKey(false)
    val categoryId: Int? = null,
    val venueDetailId: String = "",
    val name: String = "",
    val prefix: String? = "",
    val suffix: String? = ""

)
