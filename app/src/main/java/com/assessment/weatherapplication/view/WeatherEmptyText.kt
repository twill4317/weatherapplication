package com.assessment.weatherapplication.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.assessment.weatherapplication.utility.WeatherFontUtility
import com.assessment.weatherapplication.viewmodel.VisibilityViewModel

// Composable function to display a placeholder screen when no city is selected
@Composable
fun PlaceholderScreen (visibilityViewModel: VisibilityViewModel) {
    // Collect the visibility state of the "No City Selected" text from the VisibilityViewModel
    val isTextVisible by visibilityViewModel.isEmptyTextVisible.collectAsState()

    // Use AnimatedVisibility to fade in or fade out the placeholder text based on the visibility state
    AnimatedVisibility(
        visible = isTextVisible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        // Column layout to center the content vertically and horizontally
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            // Display the message "No City Selected"
            Text(
                text = "No City Selected",
                style = TextStyle(
                    fontSize = 30.sp,
                    lineHeight = 45.sp,
                    fontFamily = WeatherFontUtility.Poppins
                ), modifier = Modifier
                    .padding(47.dp, 8.dp)
            )
            // Display the message "Please Search For A City"
            Text(
                text = "Please Search For A City",
                style = TextStyle(
                    fontSize = 15.sp,
                    lineHeight = 22.5.sp,
                    fontFamily = WeatherFontUtility.Poppins
                ), modifier = Modifier
                    .padding(47.dp, 8.dp)
            )
        }
    }
}