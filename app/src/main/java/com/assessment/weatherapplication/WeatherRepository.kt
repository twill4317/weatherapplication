package com.assessment.weatherapplication

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