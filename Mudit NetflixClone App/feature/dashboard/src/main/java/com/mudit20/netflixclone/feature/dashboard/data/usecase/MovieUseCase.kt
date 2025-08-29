package com.mudit20.netflixclone.feature.dashboard.data.usecase

import com.mudit20.netflixclone.feature.dashboard.domain.repository.MovieRepository
import javax.inject.Inject

class MovieUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke()=repository.getTrendingMovie()
}