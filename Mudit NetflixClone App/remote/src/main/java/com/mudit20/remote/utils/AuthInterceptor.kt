package com.mudit20.remote.utils

import com.mudit20.remote.utils.Constant.Companion.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .header("Authorization", "Bearer $API_KEY")
            .build()
        return chain.proceed(request)
    }
}