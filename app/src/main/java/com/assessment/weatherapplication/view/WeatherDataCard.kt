package com.assessment.weatherapplication.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.assessment.weatherapplication.utility.WeatherFontUtility
import com.assessment.weatherapplication.viewmodel.VisibilityViewModel
import com.assessment.weatherapplication.viewmodel.WeatherViewModel

// Composable function to display the weather data card
@Composable
fun WeatherDataCard(visibilityViewModel: VisibilityViewModel, weatherViewModel: WeatherViewModel) {
    // Collect the weather data and visibility state as state variables using collectAsState
    val weatherData by  weatherViewModel.weather.collectAsState()
    val isDataVisible by visibilityViewModel.isWeatherCardVisible.collectAsState()

    // AnimatedVisibility is used to show/hide the weather data card with fade animation
    AnimatedVisibility(
        visible = isDataVisible, /* Control visibility based on state */
        enter = fadeIn(), /* Fade-in animation when the view becomes visible */
        exit = fadeOut()  /* Fade-out animation when the view is hidden */
    ) {

        // If weather data is available, display it in a Row layout
        weatherData?.let { data ->
            // The main container for the weather card, with padding, background, and clickable behavior
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(16.dp)
                    .background(Color(0xFFF2F2F2), shape = RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .height(117.dp)
                    .clickable {
                        // When clicked, update visibility and trigger weather details view
                        visibilityViewModel.setWeatherCardVisibility(false)
                        visibilityViewModel.setWeatherDetailVisibility(true)
                        data.location?.let { weatherViewModel.updateWeatherFromInput(it.name) }
                    }
            ) {
                // Column for text information like location and temperature
                Box {
                    Column(Modifier.padding(16.dp)) {
                        // Display location name if available
                        data.location?.let {
                            Text(
                                text = it.name, /* Display the location's name */
                                textAlign = TextAlign.Center, /* Center the text */
                                style = TextStyle(
                                    fontSize = 20.sp, /* Font size for location */
                                    fontFamily = WeatherFontUtility.poppins /* Custom Font Poppins */
                                ),
                            )
                        }
                        // Display current temperature (converted to an integer and followed by '°')
                        Text(
                            text = "${data.current?.temp_f?.toInt()}°",
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 42.sp,
                                fontFamily = WeatherFontUtility.poppinsBold
                            )
                        )
                    }
                }
                // Box for the weather condition icon (e.g., sun, cloud, rain)
                Box(
                    Modifier
                        .width(160.dp)
                        .height(185.dp)) {
                    // Load the weather condition icon using Coil's AsyncImage
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https:" + data.current?.condition?.icon)
                            .crossfade(true)
                            .build(),
                        contentDescription = data.current?.condition?.text,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }

}