package com.mudit20.netflixclone.feature.dashboard.di

import com.mudit20.netflixclone.feature.dashboard.data.repository.MovieRepositoryImpl
import com.mudit20.netflixclone.feature.dashboard.data.usecase.MovieUseCase
import com.mudit20.netflixclone.feature.dashboard.domain.repository.MovieRepository
import com.mudit20.remote.api.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object MovieModule {
    @Provides
    @Singleton
    fun provideMovieRepository(repository: MovieRepositoryImpl): MovieRepository {
        return repository
    }
    @Singleton
    @Provides
    fun provideMovieUsecase(repository: MovieRepository): MovieUseCase{
        return MovieUseCase(repository)
    }
}