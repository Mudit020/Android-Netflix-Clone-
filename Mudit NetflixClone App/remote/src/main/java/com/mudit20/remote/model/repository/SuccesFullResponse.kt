package com.mudit20.remote.model.repository

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SuccesFullResponse(
    @SerializedName("Sucess") @Expose val success: String = "",
)
