package com.assessment.weatherapplication

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun  HomeScreen(weatherViewModel: WeatherViewModel) {
    var city by remember { mutableStateOf(TextFieldValue("")) }
    var weatherData by remember { mutableStateOf<WeatherResponse?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    var isTextVisible by remember { mutableStateOf(true) }
    var isResultVisible by remember { mutableStateOf(false) }
    var isDetailVisible by remember { mutableStateOf(false) }

    fun toggleTextVisibility () { isTextVisible = !isTextVisible }
    fun toggleResultVisibility () { isResultVisible = !isResultVisible }
    fun toggleDetailVisibility () { isDetailVisible = !isDetailVisible }
//    val poppins = FontFamily(Font(R.font.poppins))
//    val poppinsBold = FontFamily(Font(R.font.poppins_bold))

    val onTrailingIconClick: () -> Unit = {
        // Call ViewModel to fetch weather for the entered city
        if (city.text.isNotEmpty()) {
            weatherViewModel.saveSelectedCity(city.text)
            weatherViewModel.weather.observeForever {
                if (it != null ) {
                        if (isTextVisible)
                            toggleTextVisibility()
                        if(isDetailVisible)
                            toggleDetailVisibility()
                        if(!isResultVisible)
                            toggleResultVisibility()
                        weatherData = it
                        errorMessage = null
                } else {
                    errorMessage = "City not found"
                }
            }
        } else {
            if (isTextVisible)
                toggleTextVisibility()
            if(isDetailVisible)
                toggleDetailVisibility()
            if(isResultVisible)
                toggleResultVisibility()
            errorMessage = "Please enter a city"
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().background(Color.White).fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        // City Search Bar
        TextField(
            value = city,
            onValueChange = { city = it },
            placeholder = {
                Text(
                    text = "Search Location",
                    style = TextStyle(
                        fontSize = 15.sp,
                        color = Color.LightGray
                    )
                )},
            modifier = Modifier
                .padding(24.dp, 46.dp)
                .width(327.dp)
                .height(46.dp)
                .background(
                    color = Color(0xFFF2F2F2),
                    shape = RoundedCornerShape(16.dp))
                .padding(horizontal = 16.dp),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFF2F2F2),
                focusedContainerColor = Color(0xFFF2F2F2)),
            trailingIcon = {
                IconButton(onClick = onTrailingIconClick) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color(0xFFC4C4C4)
                )
                    }
            }
        )

        //placeholder text
        AnimatedVisibility(
            visible = isTextVisible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Column(
                modifier = Modifier.fillMaxSize().background(Color.White).fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            )
            {
                Text(
                    text = "No City Selected",
                    style = TextStyle(
                        fontSize = 30.sp,
                        lineHeight = 45.sp
//                        ,
//                        fontFamily = poppins
                    ), modifier = Modifier
                        .padding(47.dp, 8.dp)
                )

                Text(
                    text = "Please Search For A City",
                    style = TextStyle(
                        fontSize = 15.sp,
                        lineHeight = 22.5.sp
//                        ,
//                        fontFamily = poppins
                    ), modifier = Modifier
                        .padding(47.dp, 8.dp)
                )
            }
        }

        // Show weather data if available
        AnimatedVisibility(
            visible = isResultVisible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            weatherData?.let { data ->
                Row(horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(16.dp)
                        .background(Color(0xFFF2F2F2), shape = RoundedCornerShape(16.dp))
                        .fillMaxWidth()
                        .height(117.dp)
                        .clickable {
                            toggleResultVisibility()
                            toggleDetailVisibility()
                            weatherData?.location?.name?.let { weatherViewModel.saveSelectedCity(it) }
                        }
                ) {
                    Box {
                        Column(Modifier.padding(16.dp)) {
                            data.location?.let {
                                Text(
                                    text = it.name,
                                    textAlign = TextAlign.Center,
                                    style = TextStyle(
                                        fontSize = 20.sp
                                        //                                ,
                                        //                                fontFamily = poppinsBold
                                    ),
                                )
                            }
                            Text(
                                text = "${data.current?.temp_f?.toInt()}°",
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 42.sp
                                    //                                ,
                                    //                                fontFamily = poppinsBold
                                )
                            )
                        }
                    }
                    Box(Modifier.width(160.dp).height(185.dp)) {
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

        //Details
        AnimatedVisibility(
            visible = isDetailVisible,
            enter = fadeIn(),
            exit = fadeOut()
        )
        {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxHeight()
                    .fillMaxWidth()
                )
            {
                Box(Modifier.width(123.dp).height(113.dp)) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https:" + weatherData?.current?.condition?.icon)
                            .crossfade(true)
                            .build(),
                        contentDescription = weatherData?.current?.condition?.text,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                weatherData?.location?.let {
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
                        text = "${weatherData?.current?.temp_f?.toInt()}°",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 42.sp
//                                ,
//                                fontFamily = poppinsBold
                        )
                    )
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
                                text = weatherData?.current?.humidity.toString() + "%",
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
                                text = weatherData?.current?.uv.toString(),
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
                                text = weatherData?.current?.feelslike_f.toString(),
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
        // Show error message if any
        errorMessage?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(weatherViewModel = WeatherViewModel(WeatherRepository(RetrofitModule.weatherApiService, CityDataStore(context = LocalContext.current))))
}
