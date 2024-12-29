package com.assessment.weatherapplication.model

// WeatherResponse.kt

/*
 * The Location data class represents the location information of the weather query.
 * It includes the name of the location (city), the country, and the local time in that location.
 */
data class WeatherResponse(
    val location: Location? = null, /* Contains location details (name, country, etc.)*/
    val current: Current? = null /* Contains current weather data*/
)

/*
 * The Location data class represents the location information of the weather query.
 * It includes the name of the location (city), the country, and the local time in that location.
 */
data class Location(
    val name: String, /* The name of the location (e.g city name) */
    val country: String, /* the country where the location is situated */
    val localtime: String /* The local time at the location in string format (e.g., "2023-12-23 14:30") */
)

/*
 * The Current data class represents the current weather data for the queried location.
 * It contains various properties like temperature, humidity, and UV index, as well as
 * additional data such as the "feels-like" temperature and weather conditions.
 */
data class Current(
    val temp_c: Double, /* The current temp in celcius*/
    val temp_f: Double, /* The current temp in fahrenheit*/
    val uv: Double, /* The current UV index */
    val humidity: Int, /* The humidity level in percentage */
    val condition: Condition, /* The current weather condition (e.g "clear, "rain"  */
    val feelslike_f: Double, /* The temp that it "feels like" in fahrenheit*/
    val feelslike_c: Double /* The temp that it "feels like" celcius */
)

/*
 * The Condition data class represents the textual description of the weather condition
 * (e.g., "Clear", "Partly Cloudy", "Rainy") and an associated icon URL for representing the condition visually.
 */
data class Condition(
    val text: String, /* A short description of the weather condition*/
    val icon: String, /* The URL of the icon representing the weather condition*/
)
