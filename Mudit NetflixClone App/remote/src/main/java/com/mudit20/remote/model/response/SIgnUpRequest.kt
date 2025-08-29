package com.mudit20.remote.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class SignUpRequest(
    @SerializedName("firstName")  @Expose val firstName: String = "",
    @SerializedName("lastName")  @Expose val lastName: String = "",
    @SerializedName("age")  @Expose val age: Int = 0,
    @SerializedName("username")  @Expose val username: String = "",
    @SerializedName("password")  @Expose val password: String = "",
    @SerializedName("email")  @Expose val email: String = ""
)
