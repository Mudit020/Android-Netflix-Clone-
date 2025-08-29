package com.mudit20.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private val Context.dataStore by  preferencesDataStore("netflix_clone")
class NetflixDataStore(private  val context : Context) {
    private val IS_LOGGED_IN_KEY= booleanPreferencesKey("is_logged_in")


    fun isLoggedIn(): Flow<Boolean>{
        return context.dataStore.data.map{
            it[IS_LOGGED_IN_KEY]?:false

        }
    }

    suspend fun setLoggedIn(isLoggedIn:Boolean){
        context.dataStore.edit{
            it[IS_LOGGED_IN_KEY]=isLoggedIn

        }

    }
}