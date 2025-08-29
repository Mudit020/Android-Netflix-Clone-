package com.mudit20.remote.api

import com.mudit20.remote.model.response.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("register")
    suspend fun signUp(@Body request: LoginRequest): Response<Any>

    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<Any>
}