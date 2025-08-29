package com.mudit20.netflixclone.feature.dashboard.domain.repository

import com.mudit20.common.utils.NetworkResult
import com.mudit20.netflixclone.feature.dashboard.domain.domainModel.movie.Movie


interface MovieRepository {
    suspend fun getTrendingMovie():NetworkResult<Movie>
}