package com.assessment.weatherapplication

// WeatherResponse.kt
data class WeatherResponse(
    val location: Location? = null,
    val current: Current? = null
)

data class Location(
    val name: String,
    val country: String,
    val localtime: String
)

data class Current(
    val temp_c: Double,
    val temp_f: Double,
    val uv: Double,
    val humidity: Int,
    val condition: Condition,
    val feelslike_f: Double,
    val feelslike_c: Double
)

data class Condition(
    val text: String,
    val icon: String,
)
