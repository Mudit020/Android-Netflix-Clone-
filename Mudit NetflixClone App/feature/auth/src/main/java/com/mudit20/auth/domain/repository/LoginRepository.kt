package com.mudit20.auth.domain.repository

import com.mudit20.auth.domain.model.request.LoginDomainRequest
import com.mudit20.common.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(request: LoginDomainRequest): Flow<NetworkResult<SuccesFullDomainResponse>>
} 