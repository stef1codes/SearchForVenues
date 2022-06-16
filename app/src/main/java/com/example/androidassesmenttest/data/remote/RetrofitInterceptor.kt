package com.example.androidassesmenttest.data.remote

import com.example.androidassesmenttest.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

const val AUTHORIZATION: String = "Authorization"
const val ACCEPT: String = "Accept"

class RetrofitInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .header(name = AUTHORIZATION, value = BuildConfig.KEY)
            .header(name = ACCEPT, value = "application/json")
            .build()
        return chain.proceed(request)
    }
}
