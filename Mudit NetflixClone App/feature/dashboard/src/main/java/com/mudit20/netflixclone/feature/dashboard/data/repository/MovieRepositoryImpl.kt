package com.mudit20.netflixclone.feature.dashboard.data.repository

import com.mudit20.common.utils.NetworkResult
import com.mudit20.common.utils.NetworkResult.Success
import com.mudit20.netflixclone.feature.dashboard.data.mapper.toDomain
import com.mudit20.netflixclone.feature.dashboard.domain.domainModel.movie.Movie
import com.mudit20.netflixclone.feature.dashboard.domain.repository.MovieRepository
import com.mudit20.remote.api.MovieApi
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api: MovieApi) : MovieRepository {

    override suspend fun getTrendingMovie(): NetworkResult<Movie> {
        return try {
            val response = api.getTrendingMovies()
            Success(response.toDomain())
        } catch (e: Exception) {
            NetworkResult.Error(e.message)
        }
    }
}