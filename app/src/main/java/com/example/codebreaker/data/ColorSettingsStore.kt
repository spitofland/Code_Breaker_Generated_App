package com.example.codebreaker.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class ColorSettingsStore(private val context: Context) {

    private val backgroundColorKey = intPreferencesKey("background_color")
    private val foregroundColorKey = intPreferencesKey("foreground_color")

    val backgroundColor: Flow<Int?> = context.dataStore.data
        .map { preferences ->
            preferences[backgroundColorKey]
        }

    val foregroundColor: Flow<Int?> = context.dataStore.data
        .map { preferences ->
            preferences[foregroundColorKey]
        }

    suspend fun setBackgroundColor(color: Int) {
        context.dataStore.edit { settings ->
            settings[backgroundColorKey] = color
        }
    }

    suspend fun setForegroundColor(color: Int) {
        context.dataStore.edit { settings ->
            settings[foregroundColorKey] = color
        }
    }
}
