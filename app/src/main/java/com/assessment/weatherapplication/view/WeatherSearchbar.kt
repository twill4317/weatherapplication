package com.assessment.weatherapplication.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assessment.weatherapplication.viewmodel.VisibilityViewModel
import com.assessment.weatherapplication.viewmodel.WeatherViewModel

// A composable function for a weather search bar that allows the user to search for a city or location
@Composable
fun WeatherSearchbar(visibilityViewModel: VisibilityViewModel, weatherViewModel: WeatherViewModel) {
    // Remember and manage the state of the city text input by the user
    var cityField by remember { mutableStateOf("") }

    // Define the action when the search button (trailing icon) is clicked
    val onTrailingIconClick: () -> Unit = {
        // Check if the user has entered a city
        if (cityField.isNotEmpty()) {
            // Update the weather data based on the city input
            weatherViewModel.updateWeatherFromInput(cityField)
            // Observe and update saved city data
            weatherViewModel.observeSavedCity()
            // Update the visibility of different components using the VisibilityViewModel
            visibilityViewModel.setAllVisibilities(
                isEmptyTextVisible = false,
                isWeatherDataVisible = true,
                isWeatherDetailVisible = false
            )

        }
    }

    // TextField composable for the city search input
    TextField(
        value = cityField,
        onValueChange = { it1 ->
            // Update the state whenever the user types into the text field
            cityField = it1
        },
        placeholder = {
            // Provide a placeholder text when the input field is empty
            Text(
                text = "Search Location",
                style = TextStyle(
                    fontSize = 15.sp,
                    color = Color.LightGray
                )
            )
        },
        modifier = Modifier
            .padding(24.dp, 46.dp)
            .width(327.dp)
            .height(46.dp)
            .background(
                color = Color(0xFFF2F2F2),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 16.dp),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFFF2F2F2),
            focusedContainerColor = Color(0xFFF2F2F2)),
        trailingIcon = {
            // The trailing icon (a search button) is displayed at the right end of the text field
            IconButton(onClick = onTrailingIconClick) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color(0xFFC4C4C4)
                )
            }
        }
    )
}