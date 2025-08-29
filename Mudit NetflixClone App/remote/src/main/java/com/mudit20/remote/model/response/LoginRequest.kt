package com.mudit20.remote.model.response

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email") val email: String = "",
    @SerializedName("password") val password: String = ""
) 