package com.example.androidassesmenttest.data.remote

import com.example.androidassesmenttest.BuildConfig
import com.example.androidassesmenttest.util.Constants.ACCEPT
import com.example.androidassesmenttest.util.Constants.AUTHORIZATION
import okhttp3.Interceptor
import okhttp3.Response

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
