package com.example.w3m.core.datastore

import androidx.datastore.preferences.preferencesDataStore
import android.content.Context

private const val DATASTORE_NAME = "w3m_preferences"
val Context.dataStore by preferencesDataStore(
    name = DATASTORE_NAME
)