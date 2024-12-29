package com.assessment.weatherapplication.data.local

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

/*
@CityDataStore is a class responsible for managing a local storage using Jetpack DataStore to persist
the last selected city name. This class provides functions to save and retrieve the city name.
The city name is stored as a key-value pair where the key is "selected_city" and the value is the city name.
 */
class CityDataStore @Inject
constructor (private val context: Context) {
    // Tag for logging purposes, typically used to identify logs from this class
    val TAG = this.javaClass.kotlin.simpleName
    // The name of the shared preferences file for the datastore
    val DATASTORE_PREFERENCE = "weather_preference"
    // Extension property to get a reference to the DataStore instance for preferences
    val Context.dataStore by preferencesDataStore(name = DATASTORE_PREFERENCE)
    // A Flow of the preferences data from the DataStore
    val preference = context.dataStore.data

    // Object to hold preference keys, making them easier to manage and avoid errors with hardcoded strings
    private object PreferenceKeys {
        // Key for storing the selected city in preferences
        val CITY_NAME = stringPreferencesKey("selected_city")
    }
    // A Flow that retrieves the selected city name from DataStore and transforms it into a string
    val userCityFlow: Flow<String> = preference.catch { exception ->
        // If there's an error (e.g., DataStore is unavailable), log the error and emit an empty preferences object
        Log.e(TAG, "error setting userCityFlow: ${exception.message}")
        emit(emptyPreferences())
    }.map { preferences ->
        // Retrieve the city name from preferences, or return an empty string if not found
        preferences[PreferenceKeys.CITY_NAME].toString()
    }

    // Function to save the city name to DataStore
    suspend fun saveCity(cityName: String) {
        try {
            // Edit the DataStore and save the city name under the CITY_NAME key
            context.dataStore.edit { preferences ->
                preferences[PreferenceKeys.CITY_NAME] = cityName
            }
            // Log success if city was saved successfully
            Log.i(TAG, "saveCity: city saved successfully")
        } catch (e: Exception) {
            // If there was an error saving the city, log the error message
            Log.e(TAG, "saveCity: error - ${e.message}", )
        }
    }

    // Function to log the city data currently stored in DataStore (for debugging purposes)
    suspend fun logCityData() {
        // Get the stored city name as the first emitted value from the DataStore
        val preferenceHolder = context.dataStore.data.first()[PreferenceKeys.CITY_NAME]
        // Log the current value of the stored city name
        Log.i(TAG, "logCityData: city- $preferenceHolder")
    }
}