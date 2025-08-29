package com.mudit20.auth.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mudit20.auth.domain.model.request.LoginDomainRequest
import com.mudit20.auth.domain.repository.LoginRepository
import com.mudit20.auth.ui.state.LoginIntent
import com.mudit20.auth.ui.state.LoginState
import com.mudit20.common.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) : ViewModel() {
    private val _state = MutableStateFlow<LoginState>(LoginState.Idle)
    val state: StateFlow<LoginState> = _state

    val intent = Channel<LoginIntent>(Channel.UNLIMITED)

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            intent.consumeAsFlow().collect {
                when (it) {
                    is LoginIntent.Login -> login(it.request)
                }
            }
        }
    }

    private fun login(request: LoginDomainRequest) {
        viewModelScope.launch {
            _state.value = LoginState.Loading
            try {
                val response = loginRepository.login(request)
                response.collect { result ->
                    when (result) {
                        is NetworkResult.Success -> _state.value = LoginState.Success(result.data?.success.toString())
                        is NetworkResult.Error -> _state.value = LoginState.Error(result.message.toString())
                        is NetworkResult.Loading -> _state.value = LoginState.Loading
                    }
                }
            } catch (e: Exception) {
                _state.value = LoginState.Error(e.message.toString())
            }
        }
    }
} 