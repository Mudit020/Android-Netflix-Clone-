package com.mudit20.auth.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mudit20.auth.domain.model.request.SignUpDomainRequest
import com.mudit20.auth.domain.repository.AuthRepository
import com.mudit20.auth.ui.state.AuthIntent
import com.mudit20.auth.ui.state.AuthState
import com.mudit20.common.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consume
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel@Inject constructor(private val authRepository: AuthRepository):ViewModel() {
    private val _State = MutableStateFlow<AuthState>(AuthState.Idle)
    val state: StateFlow <AuthState> =_State

    val intent= Channel<AuthIntent>(Channel.UNLIMITED)

    init{
        handleIntent()
    }

    private fun handleIntent(){
        viewModelScope.launch {
            intent.consumeAsFlow().collect {
                when(it){
                    is AuthIntent.SignUp ->signUp(it.request)
                    is AuthIntent.Login ->login(it.email, it.password)
                }
            }
        }
    }
    private fun signUp(request: SignUpDomainRequest){
        viewModelScope.launch {
            _State.value = AuthState.Loading
            try {
                val response = authRepository.signUp(request)
                response.collect { result ->
                    when (result) {
                        is NetworkResult.Success -> _State.value=AuthState.SignUpSuccess(result.data?.success.toString())
                        is NetworkResult.Error -> _State.value=AuthState.Error(result.message.toString())
                        is NetworkResult.Loading -> _State.value=AuthState.Loading
                        

                    }
                }

            }catch (e: Exception){
                _State.value= AuthState.Error(e.message.toString())
            }
        }

    }

    private fun login(email: String, password: String) {
        viewModelScope.launch {
            _State.value = AuthState.Loading
            try {
                val response = authRepository.login(email, password)
                response.collect { result ->
                    when (result) {
                        is NetworkResult.Success -> _State.value = AuthState.LoginSuccess(result.data?.success.toString())
                        is NetworkResult.Error -> _State.value = AuthState.Error(result.message.toString())
                        is NetworkResult.Loading -> _State.value = AuthState.Loading
                    }
                }
            } catch (e: Exception) {
                _State.value = AuthState.Error(e.message.toString())
            }
        }
    }
}