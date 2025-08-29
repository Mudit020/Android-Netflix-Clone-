package com.mudit20.auth.domain.model.request

data class LoginDomainRequest(
    val email: String = "",
    val password: String = ""
) 