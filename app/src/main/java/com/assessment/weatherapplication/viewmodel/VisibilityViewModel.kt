package com.assessment.weatherapplication.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

// Marking this ViewModel class with @HiltViewModel to indicate that it will be injected via Hilt
@HiltViewModel
class VisibilityViewModel
@Inject constructor():ViewModel() {
    // MutableStateFlow is used for holding and updating the visibility states of various UI components
    // These variables are private to ensure they can only be modified inside the ViewModel
    private val _isEmptyTextVisible = MutableStateFlow(false)
    private val _isWeatherCardVisible = MutableStateFlow(false)
    private val _isWeatherDetailVisible = MutableStateFlow(false)

    // Publicly exposed as StateFlow to observe the visibility state in Composables or other observers
    val isEmptyTextVisible: StateFlow<Boolean> = _isEmptyTextVisible
    val isWeatherCardVisible: StateFlow<Boolean> = _isWeatherCardVisible
    val isWeatherDetailVisible: StateFlow<Boolean> = _isWeatherDetailVisible

    // Private helper function to update the visibility of the "empty text" (e.g., "No City Selected")
    private fun setEmptyTextVisibility(isVisible: Boolean) {
        // Check if the visibility state has changed, only update if different to avoid unnecessary updates
        if (_isEmptyTextVisible.value != isVisible)
            _isEmptyTextVisible.value = isVisible
    }

    // Public function to update the visibility of the weather card
    fun setWeatherCardVisibility(isVisible: Boolean) {
        // Only update the state if the visibility has changed
        if (_isWeatherCardVisible.value != isVisible)
            _isWeatherCardVisible.value = isVisible
    }

    // Public function to update the visibility of the weather detail card
    fun setWeatherDetailVisibility(isVisible: Boolean) {
        // Only update the state if the visibility has changed
        if (_isWeatherDetailVisible.value != isVisible)
            _isWeatherDetailVisible.value = isVisible
    }

    // Public function to update the visibility of all UI components (empty text, weather card, weather details)
    // This function is used to set multiple visibility states at once.
    fun setAllVisibilities(
        isEmptyTextVisible: Boolean,
        isWeatherDataVisible: Boolean,
        isWeatherDetailVisible: Boolean
    ){
        // Call the individual setter functions to update the visibility of each component
        setEmptyTextVisibility(isEmptyTextVisible)
        setWeatherCardVisibility(isWeatherDataVisible)
        setWeatherDetailVisibility(isWeatherDetailVisible)
    }
}