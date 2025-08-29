package com.mudit20.datastore.module

import android.content.Context
import com.mudit20.datastore.NetflixDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataStoreModule {

    @Provides
    @Singleton
    fun provideNetflixDataStore(@ApplicationContext context: Context): NetflixDataStore {
        return NetflixDataStore(context)
    }
}