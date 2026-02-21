package com.example.codebreaker.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.codebreaker.data.ColorSettingsStore
import com.example.codebreaker.viewmodel.ColorSettingsViewModel
import com.example.codebreaker.viewmodel.ColorSettingsViewModelFactory

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

@Composable
fun CodeBreakerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorSettingsViewModel: ColorSettingsViewModel = viewModel(
        factory = ColorSettingsViewModelFactory(ColorSettingsStore(LocalContext.current))
    )
    val backgroundColor by colorSettingsViewModel.backgroundColor.collectAsState()
    val foregroundColor by colorSettingsViewModel.foregroundColor.collectAsState()

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }.let {
        it.copy(
            background = backgroundColor ?: it.background,
            onBackground = foregroundColor ?: it.onBackground,
            surface = backgroundColor ?: it.surface,
            onSurface = foregroundColor ?: it.onSurface
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
