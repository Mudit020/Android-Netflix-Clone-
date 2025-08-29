package com.mudit20.auth.data.mapper

import com.mudit20.auth.domain.model.request.LoginDomainRequest
import com.mudit20.remote.model.response.LoginRequest

fun LoginDomainRequest.toDomain(): LoginRequest {
    return LoginRequest(
        email = email,
        password = password
    )
} 