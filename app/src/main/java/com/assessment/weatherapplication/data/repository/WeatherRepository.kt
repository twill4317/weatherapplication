package com.assessment.weatherapplication.data.repository

import com.assessment.weatherapplication.data.local.CityDataStore
import com.assessment.weatherapplication.data.remote.WeatherApiService
import com.assessment.weatherapplication.model.WeatherResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class WeatherRepository @Inject constructor(
    private val weatherApiService: WeatherApiService,
    private val cityDataStore: CityDataStore
): WeatherRepoInterface {

    private val apiKey = "YOUR API KEY"

    override suspend fun getWeather(city: String): Result<WeatherResponse> {
        return try {
            Result.success(weatherApiService.getCurrentWeather(apiKey, city))
        } catch (e: Exception) {
            Result.failure(e.cause!!)
        }
    }
    override suspend fun saveSelectedCity(cityName: String) {
        cityDataStore.saveCity(cityName)
    }
    override fun getSavedCity(): Flow<String?> {
        return cityDataStore.getSavedCity()
    }
}