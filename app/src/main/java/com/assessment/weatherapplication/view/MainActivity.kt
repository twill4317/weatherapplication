package com.assessment.weatherapplication.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.assessment.weatherapplication.viewmodel.VisibilityViewModel
import com.assessment.weatherapplication.viewmodel.WeatherViewModel

import dagger.hilt.android.AndroidEntryPoint

/*
 * MainActivity is the entry point of the application.
 * This activity initializes and sets up the main UI content using Jetpack Compose
 * and injects the necessary ViewModels to manage the weather and UI visibility states.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    // ViewModel to manage weather data for the UI
    private val weatherViewModel: WeatherViewModel by viewModels()
    // ViewModel to manage visibility states of UI components
    private val visibilityViewModel: VisibilityViewModel by viewModels()

    /*
     * onCreate is called when the activity is created.
     * It sets up the content view and enables edge-to-edge display (making use of the full screen).
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enable edge-to-edge display, making the app use the entire screen area (including the status bar)
        enableEdgeToEdge()
        /*
        * setContent defines the composable UI content for the activity.
        * HomeScreen composable is passed the two ViewModels:
        * - weatherViewModel: Handles fetching and providing weather data
        * - visibilityViewModel: Manages visibility states for various UI components
        */
        setContent {
            HomeScreen(
                weatherViewModel = weatherViewModel,
                visibilityViewModel = visibilityViewModel)
        }
    }
}

