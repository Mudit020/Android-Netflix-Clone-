package com.mudit20.auth.domain.model.request

data class SignUpDomainRequest(
    val name: String?="",
    val email: String?="",
    val password: String?=""

)
