package com.assessment.weatherapplication.data.remote

import com.assessment.weatherapplication.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("key") apiKey: String,
        @Query("q") city: String
    ) : WeatherResponse
}