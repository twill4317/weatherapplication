package com.assessment.weatherapplication

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun DetailComponent(weatherResponse: WeatherResponse) {
    AnimatedVisibility(
        visible = true,
        enter = fadeIn(),
        exit = fadeOut()
        )
    {
        Column(Modifier.background(Color.White), Arrangement.Center) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https:" + weatherResponse.current?.condition?.icon)
                    .crossfade(true)
                    .build(),
                contentDescription = weatherResponse.current?.condition?.text,
                modifier = Modifier.fillMaxSize()
            )
            weatherResponse.location?.let {
                Text(
                    text = it.name,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 20.sp
                        //                                ,
                        //                                fontFamily = poppinsBold
                    ),
                )
                Text(
                    text = "${weatherResponse.current?.temp_f?.toInt()}°",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 42.sp
//                                ,
//                                fontFamily = poppinsBold
                    )
                )
            }
        }

        Row(
            Modifier
                .height(75.dp)
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
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.W600,
                            fontSize = 12.sp,
                            lineHeight = 18.sp,
                            textAlign = TextAlign.Center,
                            color = Color(0xFFC4C4C4)
                        )
                    )
                    Text(
                        text = weatherResponse.current?.humidity.toString() + "%",
                        style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.W500,
                            fontSize = 15.sp,
                            lineHeight = 22.5.sp,
                            textAlign = TextAlign.Center,
                            color = Color(0xFF9A9A9A)
                        )
                    )
                }
            }

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
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.W500,
                            fontSize = 12.sp,
                            lineHeight = 18.sp,
                            textAlign = TextAlign.Center,
                            color = Color(0xFFC4C4C4)
                        )
                    )
                    Text(
                        text = weatherResponse.current?.uv.toString(),
                        style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.W500,
                            fontSize = 15.sp,
                            lineHeight = 22.5.sp,
                            textAlign = TextAlign.Center,
                            color = Color(0xFF9A9A9A)
                        )
                    )
                }
            }

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
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.W500,
                            fontSize = 8.sp,
                            lineHeight = 12.sp,
                            textAlign = TextAlign.Center,
                            color = Color(0xFFC4C4C4)
                        )
                    )
                    Text(
                        text = weatherResponse.current?.feelslike_f.toString(),
                        style = TextStyle(
                            fontFamily = FontFamily.Default,
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

@Preview
@Composable
fun PreviewDetailComponent(){
    DetailComponent(WeatherResponse())
}