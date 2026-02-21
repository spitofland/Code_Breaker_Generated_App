package com.example.codebreaker.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.codebreaker.R
import com.example.codebreaker.data.ColorSettingsStore
import com.example.codebreaker.ui.theme.CodeBreakerTheme
import com.example.codebreaker.viewmodel.ColorSettingsViewModel
import com.example.codebreaker.viewmodel.ColorSettingsViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    back: () -> Unit,
    viewModel: ColorSettingsViewModel = viewModel(
        factory = ColorSettingsViewModelFactory(ColorSettingsStore(androidx.compose.ui.platform.LocalContext.current))
    )
) {
    var selectedPage by remember { mutableStateOf<SettingsPage?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.settings_title)) },
                navigationIcon = {
                    IconButton(onClick = {
                        if (selectedPage == null) {
                            back()
                        } else {
                            selectedPage = null
                        }
                    }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.navigation_go_back),
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            if (selectedPage == null) {
                SettingsMenu {
                    selectedPage = it
                }
            } else {
                when (selectedPage) {
                    SettingsPage.WORD_CHALLENGE -> WordChallengeSettings()
                    SettingsPage.SHAPE_CHALLENGE -> ShapeChallengeSettings()
                    SettingsPage.COLOR -> ColorSettings(viewModel)
                    null -> {}
                }
            }
        }
    }
}

@Composable
fun SettingsMenu(onPageSelected: (SettingsPage) -> Unit) {
    Column {
        Text(
            text = stringResource(R.string.settings_word_game),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onPageSelected(SettingsPage.WORD_CHALLENGE) }
                .padding(16.dp)
        )
        Text(
            text = stringResource(R.string.settings_shape_game),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onPageSelected(SettingsPage.SHAPE_CHALLENGE) }
                .padding(16.dp)
        )
        Text(
            text = stringResource(R.string.settings_color_title),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onPageSelected(SettingsPage.COLOR) }
                .padding(16.dp)
        )
    }
}

enum class SettingsPage {
    WORD_CHALLENGE,
    SHAPE_CHALLENGE,
    COLOR
}

@Composable
fun WordChallengeSettings() {
    Text(
        text = stringResource(R.string.settings_word_game_page),
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun ShapeChallengeSettings() {
    Text(
        text = stringResource(R.string.settings_shape_game_page),
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun ColorSettings(viewModel: ColorSettingsViewModel) {
    val backgroundColor by viewModel.backgroundColor.collectAsState()
    val foregroundColor by viewModel.foregroundColor.collectAsState()
    Column(modifier = Modifier.padding(16.dp)) {
        ColorPicker(
            title = stringResource(R.string.settings_background_color),
            initialColor = backgroundColor ?: Color.Black,
            viewModel = viewModel,
        ) { viewModel.setBackgroundColor(it) }
        ColorPicker(
            title = stringResource(R.string.settings_foreground_color),
            initialColor = foregroundColor ?: Color.White,
            viewModel = viewModel,
        ) { viewModel.setForegroundColor(it) }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    CodeBreakerTheme {
        SettingsScreen(back = {})
    }
}
