package com.mudit20.netflixclone.feature.dashboard.screen.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mudit20.common.utils.NetworkResult
import com.mudit20.netflixclone.feature.dashboard.data.usecase.MovieUseCase
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(private val moiveusecase: MovieUseCase): ViewModel() {
    private val _moviestate = MutableStateFlow(MovieState())
    val moviestate: StateFlow<MovieState> get() = _moviestate

    init {
        getTrendingMovie()

    }

    private fun getTrendingMovie(){
        viewModelScope.launch {

            when(val response= moiveusecase.invoke()){
                is NetworkResult.Error -> _moviestate.emit(MovieState(error = response.message?:"Something Went Wrong"))
                is NetworkResult.Loading -> _moviestate.emit(MovieState(isLoading = true))
                is NetworkResult.Success -> _moviestate.emit(MovieState(movies = response.data))
            }

        }
    }

}