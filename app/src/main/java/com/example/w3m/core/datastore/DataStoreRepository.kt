package com.example.w3m.core.datastore

import kotlinx.coroutines.flow.Flow



interface DataStoreRepository {

    suspend fun setFirstOpen(value: Boolean)

    fun isFirstOpen(): Flow<Boolean>

    //suspend fun saveAccessToken(token: String)

    //fun getAccessToken(): Flow<String?>

    suspend fun clear()

}