package com.example.androidassesmenttest.data.repository

import com.example.androidassesmenttest.data.local.Entity.Relations.VenueDetailxCategoriesxIcon
import com.example.androidassesmenttest.data.local.Entity.VenueEntity
import com.example.androidassesmenttest.data.local.VenueDatabase
import com.example.androidassesmenttest.data.remote.RetrofitApi
import com.example.androidassesmenttest.data.remote.dto.VenueDetailDto.VenueDetailDto
import com.example.androidassesmenttest.domain.usecases.usecases.*
import com.example.androidassesmenttest.domain.usecases.usecases.MapVenueDetailDtoToEntity
import kotlinx.coroutines.flow.first

class VenuesRepositoryImpl(
    private val api: RetrofitApi,
    private val db: VenueDatabase,
    private val mapVenueDtoToEntity: MapVenueDtoToEntity,
    private val mapVenueDetailDtoToEntity: MapVenueDetailDtoToEntity,
    private val mapCategoryEntityUsecase: MapCategoryEntityUsecase,
    private val insertVenueDataIntoDatabaseImpl: InsertVenueDataIntoDatabase,
    private val insertVenueDetailDataIntoDatabaseImpl: InsertVenueDetailDataIntoDatabase,
) : VenuesRepository {

    override suspend fun getVenuesFromRemote(
        near: String,
        radius: String,
        limit: String,
    ): List<VenueEntity> {
        val request = api.getVenues(near, radius, limit)
        val mappedRequest = mapVenueDtoToEntity(request.resultDtos)

        insertVenueDataIntoDatabaseImpl(
            listVenue = mappedRequest
        )

        return mappedRequest
    }

    override suspend fun getVenueDetailFromRemote(id: String): List<VenueDetailxCategoriesxIcon> {
        val request: VenueDetailDto = api.getVenueDetail(id)
        val mappedRequestVenueDetail = mapVenueDetailDtoToEntity(request)
        val mappedRequestCategories = mapCategoryEntityUsecase(request)

        insertVenueDetailDataIntoDatabaseImpl.invoke(
            venueDetail = mappedRequestVenueDetail,
            mappedCategories = mappedRequestCategories
        )

        return listOf(
            VenueDetailxCategoriesxIcon(
                mappedRequestVenueDetail,
                mappedRequestCategories
            )
        )
    }

    override suspend fun getVenueDetailFromDatabase(id: String): List<VenueDetailxCategoriesxIcon> =
        db.venueDao.getVenueDetailWithCategories(id).first()

    override suspend fun getVenuesFromDatabase(): List<VenueEntity> =
        db.venueDao.getVenues().first()
}
