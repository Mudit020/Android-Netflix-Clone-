package com.mudit20.auth.data.respository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.mudit20.auth.data.mapper.toDomain
import com.mudit20.auth.domain.model.request.SignUpDomainRequest
import com.mudit20.auth.domain.repository.AuthRepository
import com.mudit20.auth.domain.repository.SuccesFullDomainResponse
import com.mudit20.common.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryimpl @Inject constructor(): AuthRepository {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override suspend fun signUp(request: SignUpDomainRequest): Flow<NetworkResult<SuccesFullDomainResponse>> =
        flow {
            emit(NetworkResult.Loading())
            try {
                firebaseAuth.createUserWithEmailAndPassword(request.email ?: "", request.password ?: "").await()
                emit(NetworkResult.Success(SuccesFullDomainResponse(success = "Sign up successful")))
            } catch (e: Exception) {
                Log.e("AuthRepository", "SignUp failed", e)
                emit(NetworkResult.Error(e.localizedMessage ?: "An unknown error occurred"))
            }
        }

    override suspend fun login(email: String, password: String): Flow<NetworkResult<SuccesFullDomainResponse>> =
        flow {
            emit(NetworkResult.Loading())
            try {
                firebaseAuth.signInWithEmailAndPassword(email, password).await()
                emit(NetworkResult.Success(SuccesFullDomainResponse(success = "Login successful")))
            } catch (e: Exception) {
                Log.e("AuthRepository", "Login failed", e)
                emit(NetworkResult.Error(e.localizedMessage ?: "An unknown error occurred"))
            }
        }
}
