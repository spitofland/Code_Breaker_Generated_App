package com.example.codebreaker.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.codebreaker.data.ColorSettingsStore
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ColorSettingsViewModel(private val colorSettingsStore: ColorSettingsStore) : ViewModel() {

    val backgroundColor: StateFlow<Color?> = colorSettingsStore.backgroundColor
        .map { it?.let { Color(it) } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    val foregroundColor: StateFlow<Color?> = colorSettingsStore.foregroundColor
        .map { it?.let { Color(it) } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    fun setBackgroundColor(color: Color) {
        viewModelScope.launch {
            colorSettingsStore.setBackgroundColor(color.toArgb())
        }
    }

    fun setForegroundColor(color: Color) {
        viewModelScope.launch {
            colorSettingsStore.setForegroundColor(color.toArgb())
        }
    }
}

class ColorSettingsViewModelFactory(private val colorSettingsStore: ColorSettingsStore) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ColorSettingsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ColorSettingsViewModel(colorSettingsStore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
