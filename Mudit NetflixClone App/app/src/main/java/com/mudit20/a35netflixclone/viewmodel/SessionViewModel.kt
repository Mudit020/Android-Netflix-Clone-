package com.mudit20.a35netflixclone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mudit20.datastore.NetflixDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(private val datastore: NetflixDataStore) : ViewModel() {
    private val _isLoggedIn= MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    init{
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        viewModelScope.launch {
            _isLoggedIn.value= datastore.isLoggedIn().first()
        }
    }

    fun setLoginStatus(isLoggedIn:Boolean){
        viewModelScope.launch {
            datastore.setLoggedIn (isLoggedIn)
            _isLoggedIn.value=isLoggedIn
        }
    }


}