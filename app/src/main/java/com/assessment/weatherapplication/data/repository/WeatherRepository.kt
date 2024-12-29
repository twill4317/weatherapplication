package com.assessment.weatherapplication.data.repository

import android.util.Log
import com.assessment.weatherapplication.BuildConfig
import com.assessment.weatherapplication.data.local.CityDataStore
import com.assessment.weatherapplication.data.remote.WeatherApiService
import com.assessment.weatherapplication.model.WeatherResponse
import javax.inject.Inject

/*
 * WeatherRepository is responsible for interacting with the remote Weather API service
 * to fetch weather data for a specific city. It implements the WeatherRepoInterface, which
 * defines the contract for the repository's weather data retrieval functions.
 *
 * The repository handles the logic for making the network request and parsing the response.
 * It uses Retrofit (via the WeatherApiService) to fetch weather data.
 */
class WeatherRepository @Inject constructor(
    private val weatherApiService: WeatherApiService /* WeatherApiService instance injected for making API calls*/
): WeatherRepoInterface {
    private val TAG = this.javaClass.kotlin.simpleName // Tag for logging purposes
    private val apiKey = BuildConfig.apiKey // API key from BuildConfig for authenticating API requests

    /*
         * Fetches weather data for the specified city. This function is defined as 'suspend'
         * because it performs a network operation, which needs to be done asynchronously using coroutines.
         *
         * @param city The name of the city for which the weather data is to be fetched.
         *
         * @return Result<WeatherResponse> A Result wrapper containing the success or failure of the operation.
         *         - On success: the Result will contain a WeatherResponse object with the weather data.
         *         - On failure: the Result will contain an exception.
         */
    override suspend fun getWeather(city: String): Result<WeatherResponse> {
        Log.i(TAG, "getWeather") // Log when the function is called
        return try {
            Log.i(TAG, "getWeather try block initiated.")
            // Make the network call via the WeatherApiService to fetch the current weather for the city.
            Result.success(weatherApiService.getCurrentWeather(apiKey, city))
        } catch (e: Exception) {
            // If an exception occurs (e.g., network error, invalid response), log the error and return failure.
            Log.e(TAG, "getWeather: ${e.message} " )
            Result.failure(e.fillInStackTrace())
        }
    }

}