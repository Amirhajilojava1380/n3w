package com.example.w3m.core.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    @ApplicationContext
    private val context: Context
):DataStoreRepository  {

    override suspend fun setFirstOpen(value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.IS_FIRST_OPEN] = value
        }
    }

    override fun isFirstOpen(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferenceKeys.IS_FIRST_OPEN] ?: true
        }
    }


    override suspend fun clear() {
        context.dataStore.edit {
            it.clear()
        }
    }


}