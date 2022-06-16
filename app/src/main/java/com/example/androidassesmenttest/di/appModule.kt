package com.example.androidassesmenttest.di

import com.example.androidassesmenttest.application.VenueApplication
import com.example.androidassesmenttest.repository.VenuesRepository
import com.example.androidassesmenttest.repository.VenuesRepositoryImpl
import com.example.androidassesmenttest.usecases.usecases.*
import com.example.androidassesmenttest.usecases.usecasesImpl.*
import com.example.androidassesmenttest.pressentation.searchVenues.viewmodel.VenuesViewModel
import com.example.androidassesmenttest.pressentation.venueDetails.viewmodel.VenueDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { VenueApplication() }

    viewModel { VenuesViewModel(get(), get()) }
    viewModel { VenueDetailsViewModel(get()) }

    single<VenuesRepository> { VenuesRepositoryImpl(get(), get(), get(), get(), get(), get(),get()) }

    single<GetVenueDetailFromRemoteUseCase> { GetVenueDetailFromRemoteUseCaseImpl(get()) }
    single<GetVenuesFromRemoteUsecase> { GetVenueFromRemoteUseCaseImpl(get()) }
    single<GetVenueDetailWithCategoriesUsecase> { GetVenueDetailWithCategoriesUsecaseImpl(get()) }

    single<InsertVenueDataIntoDatabase> { InsertVenueDataIntoDatabaseImpl(get()) }
    single<InsertVenueDetailDataIntoDatabase> { InsertVenueDetailDataIntoDatabaseImpl(get()) }

    single<MapVenueDtoToEntity> { MapVenueDtoToEntityImpl() }
    single<MapVenueDetailDtoToEntity> { MapVenueDetailDtoToEntityImpl() }
    single<MapCategoryEntityUsecase> { MapCategoryEntityImpl() }

    single { ConnectionObservation(get()) }

}
