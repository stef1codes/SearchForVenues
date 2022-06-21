package com.example.androidassesmenttest.di

import com.example.androidassesmenttest.BuildConfig
import com.example.androidassesmenttest.data.remote.FourSquareApi
import com.example.androidassesmenttest.data.remote.RetrofitInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    factory { RetrofitInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideRetrofitApi(get()) }
    factory { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.MY_API).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(authInterceptor: RetrofitInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .addNetworkInterceptor(
            HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }
        ).build()
}

fun provideRetrofitApi(retrofit: Retrofit): FourSquareApi =
    retrofit.create(FourSquareApi::class.java)
