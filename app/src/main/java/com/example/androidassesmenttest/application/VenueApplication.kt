package com.example.androidassesmenttest.application

import android.app.Application
import com.example.androidassesmenttest.di.appModule
import com.example.androidassesmenttest.di.retrofitModule
import com.example.androidassesmenttest.di.roomModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class VenueApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@VenueApplication)
            modules(
                modules = listOf(
                    appModule,
                    retrofitModule,
                    roomModule
                )
            )
        }
    }
}
