package com.mudit20.auth.data.respository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.mudit20.auth.data.mapper.toDomain
import com.mudit20.auth.domain.model.request.LoginDomainRequest
import com.mudit20.auth.domain.repository.LoginRepository
import com.mudit20.auth.domain.repository.SuccesFullDomainResponse
import com.mudit20.common.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor() : LoginRepository {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override suspend fun login(request: LoginDomainRequest): Flow<NetworkResult<SuccesFullDomainResponse>> =
        flow {
            emit(NetworkResult.Loading())
            try {
                firebaseAuth.signInWithEmailAndPassword(request.email, request.password).await()
                emit(NetworkResult.Success(SuccesFullDomainResponse(success = "Login successful")))
            } catch (e: Exception) {
                Log.e("LoginRepository", "Login failed", e)
                emit(NetworkResult.Error(e.localizedMessage ?: "An unknown error occurred"))
            }
        }
} 