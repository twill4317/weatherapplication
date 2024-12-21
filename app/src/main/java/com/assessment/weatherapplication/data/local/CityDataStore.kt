package com.assessment.weatherapplication.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CityDataStore @Inject
constructor (private val context: Context) {
    val Context.dataStore by preferencesDataStore(name = "weather_preference")


    private object PreferenceKeys {
        val CITY_NAME = stringPreferencesKey("selected_city")
    }


    // Save the selected city to DataStore
    suspend fun saveCity(cityName: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.CITY_NAME] = cityName
        }
    }

    fun getSavedCity(): Flow<String?> {
        return context.dataStore.data
            .map {
                preferences ->
                preferences[PreferenceKeys.CITY_NAME]
            }
    }
}