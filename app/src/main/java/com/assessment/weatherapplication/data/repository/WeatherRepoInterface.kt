package com.assessment.weatherapplication.data.repository

import com.assessment.weatherapplication.model.WeatherResponse

/*
 * WeatherRepoInterface defines the contract for the weather repository.
 * Any class implementing this interface must provide an implementation for fetching weather data.
 */
interface WeatherRepoInterface {
    /*
     * Defines a suspend function to fetch weather data for a given city.
     * This is a contract that any class implementing this interface must adhere to.
     *
     * @param city The name of the city for which the weather data is to be fetched.
     *
     * @return Result<WeatherResponse> A Result wrapper containing either the weather data on success
     *         or an error (exception) on failure.
     */
    suspend fun getWeather(city: String): Result<WeatherResponse>
}