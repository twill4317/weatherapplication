package com.assessment.weatherapplication.di

import com.assessment.weatherapplication.data.remote.WeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/*
 * The RetrofitModule class is a Dagger module responsible for providing a singleton Retrofit instance
 * and exposing the WeatherApiService interface. This module ensures that Retrofit and its dependencies
 * are properly configured and injected wherever needed within the app.
 */

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    // The base URL for the weather API
    const val BASE_URL = " https://api.weatherapi.com/v1/"

    /*
     * Lazy initialization of Retrofit instance.
     * Retrofit.Builder() sets up the Retrofit object with the necessary configurations:
     *   - baseUrl() sets the base URL for all API calls.
     *   - addConverterFactory() tells Retrofit to use GsonConverterFactory to handle JSON responses.
     *   - build() creates the Retrofit object using the above configurations.
     */
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Lazy initialization of WeatherApiService using Retrofit to create the service
    val weatherApiService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }

    /*
     * Provides a singleton instance of WeatherApiService for dependency injection.
     * Dagger will inject the WeatherApiService wherever it is required in the app.
     */
    @Provides
    @Singleton // Ensures the service is a singleton throughout the app.
    fun provideService() : WeatherApiService {
        // Returning the singleton instance of WeatherApiService
        return weatherApiService
    }
}