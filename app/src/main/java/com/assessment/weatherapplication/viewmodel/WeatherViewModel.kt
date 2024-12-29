package com.assessment.weatherapplication.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assessment.weatherapplication.data.local.CityDataStore
import com.assessment.weatherapplication.data.repository.WeatherRepository
import com.assessment.weatherapplication.model.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel class to manage the weather data and handle business logic
@HiltViewModel
class WeatherViewModel @Inject constructor
    (val repository: WeatherRepository, val datastore: CityDataStore): 
    ViewModel()
{
    // TAG for logging purposes, holds the class name for easy identification in logs
    val TAG = this.javaClass.kotlin.simpleName

    // MutableStateFlow to hold weather data, error messages, and saved city data.
    private val _weather = MutableStateFlow<WeatherResponse?>(null)
    private val _errorMessage = MutableStateFlow<String?>(null)
    private val _savedCity = MutableStateFlow<String?>(null)

    private val errorMessage: StateFlow<String?> get() = _errorMessage
    // Public StateFlow properties to expose weather, error message, and saved city for observing in UI
    val weather: StateFlow<WeatherResponse?> get() = _weather
    val savedCity: StateFlow<String?> get() = _savedCity

    // Init block that triggers the observation of the saved city when the ViewModel is created
    init {
        observeSavedCity()
    }
    // Observes changes to the saved city in the datastore and fetches weather data accordingly
     fun observeSavedCity() {
        Log.i(TAG, "observeSavedCity")
        // Launch a coroutine to observe the flow of saved city from the datastore
        viewModelScope.launch {
            // Collecting the flow of saved city data
            datastore.userCityFlow
                .onEach { city ->
                    // Log the city being observed
                    Log.i(TAG, "observeSavedCity: ${city}")
                    // Update the _savedCity state with the new city
                    _savedCity.value = city
                        savedCity.value?.let {
                            it1 -> sendWeatherRequestWithCity(it1)
                        }
                }.collect()
        }
    }

    // Sends a request to the weather repository to fetch weather data for the given city
    private fun sendWeatherRequestWithCity(city: String) {
        Log.i(TAG, "Signature = sendWeatherRequestWithCity( ${city} )")
        // Launching a coroutine in IO context for network call to fetch weather data
        viewModelScope.launch(Dispatchers.IO) {
            // Fetching weather data for the given city
            val result = repository.getWeather(city)
            result.isSuccess.let {
                // If the request is successful, update the _weather state with the response
                _weather.value = result.getOrNull()
                // Log whether weather data is null or not
                Log.i(TAG, "Is current Weather null ?  ${weather.value ==null }")
                Log.i(TAG, "Successfully sent request with: ${weather.value?.location?.name}")
            }
            // Handling failure case of the weather request
            result.isFailure.let {
                // If the request failed, update the _errorMessage state with the error message
                _errorMessage.value = result.exceptionOrNull()?.message
                Log.i(TAG, "Error message via sendWeatherRequestWithCity: ${errorMessage.value}")
            }
        }
    }

    // Updates the saved city in the datastore and triggers a weather update
    fun updateWeatherFromInput(city: String) {
        Log.i(TAG, "Weather updated to ${city}")
        // Launch a coroutine to save the city in the datastore
        viewModelScope.launch {
            datastore.saveCity(city)
        }
    }

    // Returns the current weather data
    fun getWeather(): WeatherResponse? {
        return weather.value
    }

}
