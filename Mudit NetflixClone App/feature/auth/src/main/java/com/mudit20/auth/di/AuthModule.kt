package com.mudit20.auth.di

import com.mudit20.auth.data.respository.AuthRepositoryimpl
import com.mudit20.auth.data.respository.LoginRepositoryImpl
import com.mudit20.auth.domain.repository.AuthRepository
import com.mudit20.auth.domain.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AuthModule {

    @Singleton
    @Provides
    fun provideAuthRepository(): AuthRepository {
        return AuthRepositoryimpl()
    }

    @Singleton
    @Provides
    fun provideLoginRepository(): LoginRepository {
        return LoginRepositoryImpl()
    }
}