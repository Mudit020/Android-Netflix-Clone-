package com.mudit20.auth.ui.state

import com.mudit20.auth.domain.model.request.LoginDomainRequest

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Error(val error: String) : LoginState()
    data class Success(val message: String) : LoginState()
}

sealed class LoginIntent {
    data class Login(val request: LoginDomainRequest) : LoginIntent()
} 