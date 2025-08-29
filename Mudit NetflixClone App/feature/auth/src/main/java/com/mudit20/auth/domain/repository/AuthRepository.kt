package com.mudit20.auth.domain.repository

import com.mudit20.auth.domain.model.request.SignUpDomainRequest
import com.mudit20.common.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signUp(request: SignUpDomainRequest): Flow<NetworkResult<SuccesFullDomainResponse>>
    suspend fun login(email: String, password: String): Flow<NetworkResult<SuccesFullDomainResponse>>
}