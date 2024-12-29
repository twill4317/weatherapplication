package com.assessment.weatherapplication.utility

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.assessment.weatherapplication.R

// Utility object to manage custom fonts for the weather application
object WeatherFontUtility {
    // FontFamily object to store and provide access to the Poppins font in different styles
    val Poppins = FontFamily(
        // Regular weight of the Poppins font
        Font(R.font.poppins),
        // Bold weight of the Poppins font
        Font(R.font.poppins_bold),
    )
}