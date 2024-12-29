package com.assessment.weatherapplication.utility

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.assessment.weatherapplication.R

// Utility object to manage custom fonts for the weather application
object WeatherFontUtility {
    // FontFamily object to store and provide access to the Poppins font in different styles
    // Regular weight of the Poppins font
    val poppins = FontFamily(Font(R.font.poppins))
    // Bold weight of the Poppins font
    val poppinsBold = FontFamily(Font(R.font.poppins_bold))

}