package com.example.androidassesmenttest.di

import com.example.androidassesmenttest.application.VenueApplication
import com.example.androidassesmenttest.data.repository.VenuesRepository
import com.example.androidassesmenttest.data.repository.VenuesRepositoryImpl
import com.example.androidassesmenttest.domain.usecases.usecases.*
import com.example.androidassesmenttest.domain.usecases.usecasesImpl.*
import com.example.androidassesmenttest.presentation.searchVenuesActivity.viewmodel.VenuesViewModel
import com.example.androidassesmenttest.presentation.venueDetailsActivity.viewmodel.VenueDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { VenueApplication() }

    viewModel { VenuesViewModel(get()) }
    viewModel { VenueDetailsViewModel(get()) }

    single<VenuesRepository> {
        VenuesRepositoryImpl(get(), get(), get(), get(), get(), get(), get())
    }

    single<GetVenueDetailUseCase> { GetVenueDetailUseCaseImpl(get(), get()) }
    single<GetVenuesUseCase> { GetVenuesUseCaseImpl(get(), get()) }
    single<GetVenueDetailWithCategoriesUsecase> { GetVenueDetailWithCategoriesUsecaseImpl(get()) }

    single<InsertVenueDataIntoDatabase> { InsertVenueDataIntoDatabaseImpl(get()) }
    single<InsertVenueDetailDataIntoDatabase> { InsertVenueDetailDataIntoDatabaseImpl(get()) }

    single<MapVenueDtoToEntity> { MapVenueDtoToEntityImpl() }
    single<MapVenueDetailDtoToEntity> { MapVenueDetailDtoToEntityImpl() }
    single<MapCategoryEntityUsecase> { MapCategoryEntityImpl() }

    single { ConnectionObservation(get()) }
}
