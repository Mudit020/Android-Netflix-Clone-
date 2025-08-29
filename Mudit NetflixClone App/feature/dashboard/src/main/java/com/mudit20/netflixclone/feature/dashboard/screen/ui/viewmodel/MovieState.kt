package com.mudit20.netflixclone.feature.dashboard.screen.ui.viewmodel

import com.mudit20.netflixclone.feature.dashboard.domain.domainModel.movie.Movie

data class MovieState(
    val isLoading: Boolean = false,
    val movies: Movie? = null,
    val error: String = ""

)
