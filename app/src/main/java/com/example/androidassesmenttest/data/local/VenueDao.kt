package com.example.androidassesmenttest.data.local

import androidx.room.*
import com.example.androidassesmenttest.data.local.Entity.CategoryEntity
import com.example.androidassesmenttest.data.local.Entity.Relations.VenueDetailxCategoriesxIcon
import com.example.androidassesmenttest.data.local.Entity.VenueDetailEntity
import com.example.androidassesmenttest.data.local.Entity.VenueEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VenueDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVenues(venue: List<VenueEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVenueDetail(venue: VenueDetailEntity)

    @Transaction
    @Query("SELECT * FROM venue_table")
    fun getVenues(): Flow<List<VenueEntity>>

    @Query("DELETE FROM venue_table")
    suspend fun deleteAllFromVenues()

    @Query("DELETE FROM venue_detail_table")
    suspend fun deleteAllFromVenueDetail()

    @Transaction
    @Query("SELECT * FROM venue_detail_table WHERE venueDetailId = :id")
    fun getVenueDetailWithCategories(id: String): Flow<List<VenueDetailxCategoriesxIcon>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategories(mappedCategories: List<CategoryEntity>)

    @Query("DELETE FROM category_table")
    suspend fun deleteAllFromCategories()
}
