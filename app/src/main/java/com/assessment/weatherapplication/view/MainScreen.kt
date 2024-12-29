package com.assessment.weatherapplication.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.assessment.weatherapplication.data.local.CityDataStore
import com.assessment.weatherapplication.di.RetrofitModule
import com.assessment.weatherapplication.data.repository.WeatherRepository
import com.assessment.weatherapplication.viewmodel.VisibilityViewModel
import com.assessment.weatherapplication.viewmodel.WeatherViewModel

/*
 * HomeScreen Composable
 * This composable serves as the main UI of the app, showing weather data, search options, and placeholders
 * based on the state of the weather data. It interacts with both the WeatherViewModel and VisibilityViewModel
 * to control data display and visibility states of UI components.
 */
@Composable
fun  HomeScreen(weatherViewModel: WeatherViewModel, visibilityViewModel: VisibilityViewModel) {
    // Log tag for identifying logs related to this composable function
    val TAG = "HomeScreen Composable"
    // Collects the weather data from the weatherViewModel's state
    val weatherData by  weatherViewModel.weather.collectAsState()

    // Function to toggle visibility states for various UI components (search bar, empty text, weather data, etc.)
    fun toggleVisibilities(
        isEmptyTextVisible: Boolean,
        isWeatherDataVisible: Boolean,
        isWeatherDetailVisible: Boolean
    ) {
        // Set visibility states using the VisibilityViewModel
        visibilityViewModel.setAllVisibilities(
            isEmptyTextVisible = isEmptyTextVisible,
            isWeatherDataVisible = isWeatherDataVisible,
            isWeatherDetailVisible = isWeatherDetailVisible
        )
    }

    // LaunchedEffect ensures that this block is re-executed whenever `weatherData` changes
    LaunchedEffect(weatherData) {
        // Observes the saved city from the weatherViewModel (e.g., from DataStore)
        weatherViewModel.observeSavedCity()
        // Log the state of weatherData for debugging purposes
        Log.i(TAG, "HomeScreen: weatherData is equal Null?  ${weatherData==null}")

        // Toggle visibility based on whether weather data is available or not
        if (weatherData != null ) {
            // Show weather data and hide placeholder when weather data is available
            toggleVisibilities(
                isEmptyTextVisible = false,
                isWeatherDataVisible = true,
                isWeatherDetailVisible = false)
        } else {
            // Show empty text and hide weather data when no data is available
            toggleVisibilities(
                isEmptyTextVisible = true,
                isWeatherDataVisible = false,
                isWeatherDetailVisible = false)
        }
    }

    // Column is used to arrange the UI components vertically with a white background
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        // WeatherSearchbar displays a search bar for the user to search for cities
        WeatherSearchbar(visibilityViewModel, weatherViewModel)
        // PlaceholderScreen is shown when there's no weather data available
        PlaceholderScreen(visibilityViewModel)
        // WeatherDataCard displays the weather information when available
        WeatherDataCard(visibilityViewModel, weatherViewModel)
        // WeatherDetailCard displays detailed weather information when available
        WeatherDetailCard(visibilityViewModel, weatherViewModel)
    }
}

/*
 * Preview function to visualize the HomeScreen composable in Android Studio's Preview
 * This allows for a static preview of the UI, even when the app is not running.
 */
@Preview
@Composable
fun PreviewHomeScreen() {
    // Preview the HomeScreen with mock WeatherViewModel and VisibilityViewModel
    HomeScreen(weatherViewModel = WeatherViewModel(
        WeatherRepository(
            RetrofitModule.weatherApiService), CityDataStore(LocalContext.current)
    ), VisibilityViewModel()
    )
}
