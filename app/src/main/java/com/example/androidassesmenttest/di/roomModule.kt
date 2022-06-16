package com.example.androidassesmenttest.di

import androidx.room.Room
import com.example.androidassesmenttest.data.local.VenueDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val roomModule = module {

    single {
        Room.databaseBuilder(androidApplication(), VenueDatabase::class.java, "venueDatabase")
            .fallbackToDestructiveMigration().build()
    }

    single { get<VenueDatabase>().venueDao }


}


