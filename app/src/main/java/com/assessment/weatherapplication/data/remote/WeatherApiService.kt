package com.assessment.weatherapplication.data.remote

import com.assessment.weatherapplication.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query
/*
 * WeatherApiService is an interface that defines the API endpoints used to fetch weather data.
 * It utilizes Retrofit annotations to specify how network requests should be made.
 * This interface acts as the contract between the Retrofit library and the weather API.
 *
 * In this case, we have one endpoint: `getCurrentWeather` that fetches the current weather data for a given city.
 */
interface WeatherApiService {
    /*
     * This function retrieves the current weather data for a specific city.
     * It is marked as `suspend` because it is designed to be called from a coroutine.
     *
     * The `@GET("current.json")` annotation specifies that this function should make a GET request
     * to the `current.json` endpoint on the weather API server.
     *
     * @param apiKey The API key required for authenticating the request. This key is passed as a query parameter.
     * @param city The name of the city for which we want to fetch the weather data. This city name is also passed as a query parameter.
     *
     * @return A `WeatherResponse` object, which will contain the weather data returned from the API.
     *         This is the model that we expect in response, as defined in the `WeatherResponse` class.
     */
    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("key") apiKey: String, // The API key for authentication.
        @Query("q") city: String // the city name for which the weather is requested.
    ) : WeatherResponse // The response data is wrapped in a WeatherResponse model
}