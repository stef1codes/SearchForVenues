package com.example.androidassesmenttest.repository

import com.example.androidassesmenttest.data.local.Entity.Relations.VenueDetailxCategoriesxIcon
import com.example.androidassesmenttest.data.local.Entity.VenueEntity
import com.example.androidassesmenttest.data.local.VenueDatabase
import com.example.androidassesmenttest.data.remote.FourSquareApi
import com.example.androidassesmenttest.data.remote.dto.VenueDetailDto.VenueDetailDto
import com.example.androidassesmenttest.usecases.usecases.*
import kotlinx.coroutines.flow.first

class VenuesRepositoryImpl(
    private val api: FourSquareApi,
    private val db: VenueDatabase,
    private val mapVenueDtoToEntity: MapVenueDtoToEntity,
    private val mapVenueDetailDtoToEntity: MapVenueDetailDtoToEntity,
    private val mapCategoryEntityUsecase: MapCategoryEntityUsecase,
    private val insertVenueDataIntoDatabaseImpl: InsertVenueDataIntoDatabase,
    private val insertVenueDetailDataIntoDatabaseImpl: InsertVenueDetailDataIntoDatabase,
) : VenuesRepository {

    override suspend fun getVenuesRemote(
        near: String,
        radius: Int?,
        limit: Int?,
    ): List<VenueEntity> {
        val request = api.getVenues(near, radius, limit)
        val mappedRequest = mapVenueDtoToEntity(request.resultDtos)
        insertVenueDataIntoDatabaseImpl(mappedRequest)

        return db.venueDao.getVenues().first()
    }

    override suspend fun getVenueDetailRemote(id: String): List<VenueDetailxCategoriesxIcon> {
        val request: VenueDetailDto = api.getVenueDetail(id)
        val mappedVenueDetail = mapVenueDetailDtoToEntity(request)
        val mappedCategories = mapCategoryEntityUsecase(request)
        insertVenueDetailDataIntoDatabaseImpl.invoke(mappedVenueDetail, mappedCategories)

        return db.venueDao.getVenueDetailWithCategories(id).first()
    }

    override suspend fun getVenueWithCategories(id: String): List<VenueDetailxCategoriesxIcon> {
        return db.venueDao.getVenueDetailWithCategories(id).first()
    }
}
