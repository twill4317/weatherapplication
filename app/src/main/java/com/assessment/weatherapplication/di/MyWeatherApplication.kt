package com.assessment.weatherapplication.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyWeatherApplication : Application() {
    /*
         * The @HiltAndroidApp annotation triggers Hilt's code generation process
         * and sets up the necessary components for dependency injection throughout the app.
         *
         * This class is the entry point of the application and is required to enable Hilt.
         * It extends the Application class to give access to the app-level lifecycle and context.
         *
         * The @HiltAndroidApp annotation automatically generates the required Hilt components
         * (such as the HiltApplicationComponent) and initializes dependency injection for the app.
         * This ensures that dependencies can be injected into Android components (like Activities,
         * Fragments, ViewModels) across the entire app.
         */
}