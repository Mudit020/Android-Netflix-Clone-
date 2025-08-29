package com.mudit20.netflixclone.feature.dashboard.data.mapper

import com.mudit20.netflixclone.feature.dashboard.domain.domainModel.movie.Movie
import com.mudit20.netflixclone.feature.dashboard.domain.domainModel.movie.Result
import com.mudit20.remote.model.response.movie.MovieResponse
import com.mudit20.remote.model.response.movie.ResultResponse

fun MovieResponse.toDomain(): Movie {
    return Movie(

        results = results?.mapNotNull { it?.toDomain() } ?: emptyList()
    )
}

fun ResultResponse.toDomain(): Result {
    return Result(
        adult,
        backdropPath,genreIds,id,mediaType,originalLanguage,originalTitle,overview,popularity,posterPath,releaseDate,title,video,voteAverage,voteCount
    )
}