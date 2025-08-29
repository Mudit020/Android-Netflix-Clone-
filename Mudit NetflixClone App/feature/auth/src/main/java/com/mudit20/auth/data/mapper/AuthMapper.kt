package com.mudit20.auth.data.mapper

import com.mudit20.auth.domain.model.request.SignUpDomainRequest
import com.mudit20.auth.domain.repository.SuccesFullDomainResponse
import com.mudit20.remote.model.response.LoginRequest
import com.mudit20.remote.model.repository.SuccesFullResponse

fun SignUpDomainRequest.toDomain(): LoginRequest {
    return LoginRequest(
        email = email ?: "",
        password = password ?: ""
    )
}

fun SuccesFullResponse.toDomain(): SuccesFullDomainResponse{
    return SuccesFullDomainResponse(success=success)
}