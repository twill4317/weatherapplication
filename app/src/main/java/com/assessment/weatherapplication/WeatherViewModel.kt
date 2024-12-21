package com.assessment.weatherapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor
    (val repository: WeatherRepository): ViewModel()
{
    val TAG = this.javaClass.kotlin.simpleName

    private val _weather = MutableLiveData<WeatherResponse>()
    val weather: LiveData<WeatherResponse> get() = _weather

    private val _savedCity = MutableLiveData<String?>()
    val savedCity: LiveData<String?> get() = _savedCity

    init {
        viewModelScope.launch {
            repository.getSavedCity().collect { city ->
                _savedCity.value = city
                city?.let { fetchWeather() }
            }
        }
    }

    private fun fetchWeather() {
        viewModelScope.launch {
            val result = repository.getWeather(savedCity.value.toString())
            result.isSuccess.let { _weather.value = result.getOrNull()!! }
            result.isFailure.let { Log.e(TAG, "fetchWeather: ${result.exceptionOrNull()}" )}
        }
    }

    fun saveSelectedCity(city: String) {
        viewModelScope.launch {
            repository.saveSelectedCity(city)
        }
    }

    fun getWeather(): WeatherResponse {
        return weather.value!!
    }

}
