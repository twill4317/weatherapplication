package com.assessment.weatherapplication.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.assessment.weatherapplication.utility.WeatherFontUtility
import com.assessment.weatherapplication.viewmodel.VisibilityViewModel
import com.assessment.weatherapplication.viewmodel.WeatherViewModel

// Composable function to display the detailed weather card
@Composable
fun WeatherDetailCard(visibilityViewModel: VisibilityViewModel, weatherViewModel: WeatherViewModel){
    // Observing the weather data and visibility state from respective ViewModels
    val weatherData by weatherViewModel.weather.collectAsState()
    val isDetailVisible by visibilityViewModel.isWeatherDetailVisible.collectAsState()
    // AnimatedVisibility to show or hide the detailed weather information with fade-in/out animations
        AnimatedVisibility(
            visible = isDetailVisible,
            enter = fadeIn(),
            exit = fadeOut()
        )
        {
            // Column layout to arrange weather details vertically
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxHeight()
                    .fillMaxWidth()
                )
            {
                // Display the weather condition icon using AsyncImage (loaded with Coil)
                Box(
                    Modifier
                        .width(123.dp)
                        .height(123.dp)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https:" + weatherData?.current?.condition?.icon)
                            .crossfade(true)
                            .build(),
                        contentDescription = weatherData?.current?.condition?.text,
                        modifier = Modifier.fillMaxSize()

                    )
                }
                // Display the location name and temperature (if available) from weatherData
                weatherData?.location?.let {
                    // Location name text
                    Text(
                        text = it.name,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 30.sp,
                            fontFamily = WeatherFontUtility.poppinsBold
                        ),
                    )
                    // Temperature text (rounded to an integer and displayed in degrees)
                    Text(
                        text = "${weatherData?.current?.temp_f?.toInt()}°",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontFamily = WeatherFontUtility.poppinsBold,
                            fontWeight = FontWeight.W500,
                            fontSize = 70.sp,
                            lineHeight = 105.sp
                        )
                    )
                }
                // Row to display additional weather details like Humidity, UV, and Feels Like
                Row(
                    Modifier
                        .height(120.dp)
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(
                            Color(0xFFF2F2F2),
                            shape = RoundedCornerShape(16.dp)
                        ),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically

                )
                {
                    // Box for displaying the "Humidity" information
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column {
                            Text(
                                text = "Humidity",
                                style = TextStyle(
                                    fontFamily = WeatherFontUtility.poppins,
                                    fontWeight = FontWeight.W600,
                                    fontSize = 12.sp,
                                    lineHeight = 18.sp,
                                    textAlign = TextAlign.Center,
                                    color = Color(0xFFC4C4C4)
                                )
                            )
                            Text(
                                text = weatherData?.current?.humidity.toString() + "%",
                                style = TextStyle(
                                    fontFamily = WeatherFontUtility.poppins,
                                    fontWeight = FontWeight.W500,
                                    fontSize = 15.sp,
                                    lineHeight = 22.5.sp,
                                    textAlign = TextAlign.Center,
                                    color = Color(0xFF9A9A9A)
                                )
                            )
                        }
                    }

                    // Box for displaying the "UV" information
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column {
                            Text(
                                text = "UV",
                                style = TextStyle(
                                    fontFamily = WeatherFontUtility.poppins,
                                    fontWeight = FontWeight.W500,
                                    fontSize = 12.sp,
                                    lineHeight = 18.sp,
                                    textAlign = TextAlign.Center,
                                    color = Color(0xFFC4C4C4)
                                )
                            )
                            Text(
                                text = weatherData?.current?.uv.toString(),
                                style = TextStyle(
                                    fontFamily = WeatherFontUtility.poppins,
                                    fontWeight = FontWeight.W500,
                                    fontSize = 15.sp,
                                    lineHeight = 22.5.sp,
                                    textAlign = TextAlign.Center,
                                    color = Color(0xFF9A9A9A)
                                )
                            )
                        }
                    }
                    // UV index value
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column {
                            Text(
                                text = "Feels Like",
                                style = TextStyle(
                                    fontFamily = WeatherFontUtility.poppins,
                                    fontWeight = FontWeight.W500,
                                    fontSize = 8.sp,
                                    lineHeight = 12.sp,
                                    textAlign = TextAlign.Center,
                                    color = Color(0xFFC4C4C4)
                                )
                            )
                            Text(
                                text = weatherData?.current?.feelslike_f.toString(),
                                style = TextStyle(
                                    fontFamily = WeatherFontUtility.poppins,
                                    fontWeight = FontWeight.W500,
                                    fontSize = 15.sp,
                                    lineHeight = 22.5.sp,
                                    textAlign = TextAlign.Center,
                                    color = Color(0xFF9A9A9A)
                                )
                            )
                        }
                    }
                }
            }
        }
}