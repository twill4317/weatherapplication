package com.assessment.weatherapplication

import kotlinx.coroutines.flow.Flow

interface WeatherRepoInterface {
    suspend fun getWeather(city: String): Result<WeatherResponse>
    suspend fun saveSelectedCity(cityName: String)
    fun getSavedCity(): Flow<String?>
}