package com.mudit20.remote.api

import com.mudit20.remote.model.response.movie.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {
    @GET("trending/movie/{time_window}")
    suspend fun getTrendingMovies(@Path("time_window") timeWindow: String = "day"): MovieResponse

}