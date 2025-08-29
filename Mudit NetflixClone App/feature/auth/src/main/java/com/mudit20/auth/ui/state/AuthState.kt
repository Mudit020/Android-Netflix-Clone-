package com.mudit20.auth.ui.state

import com.mudit20.auth.domain.model.request.SignUpDomainRequest

sealed class AuthState {
     object Idle: AuthState()
     object Loading: AuthState()
     data class Error(val error:String): AuthState()
     data class SignUpSuccess(val message:String): AuthState()
     data class LoginSuccess(val message:String): AuthState()
}

sealed class AuthIntent{
    data class SignUp(val request: SignUpDomainRequest): AuthIntent()
    data class Login(val email: String, val password: String): AuthIntent()
}