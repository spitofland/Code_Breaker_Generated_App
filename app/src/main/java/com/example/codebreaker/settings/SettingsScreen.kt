package com.example.codebreaker.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen() {
    var selectedPage by remember { mutableStateOf<SettingsPage?>(null) }

    if (selectedPage == null) {
        SettingsMenu {
            selectedPage = it
        }
    } else {
        when (selectedPage) {
            SettingsPage.WORD_CHALLENGE -> WordChallengeSettings()
            SettingsPage.SHAPE_CHALLENGE -> ShapeChallengeSettings()
            SettingsPage.COLOR -> ColorSettings()
            null -> {}
        }
    }
}

@Composable
fun SettingsMenu(onPageSelected: (SettingsPage) -> Unit) {
    Column {
        Text(
            text = "Word Challenge Settings",
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onPageSelected(SettingsPage.WORD_CHALLENGE) }
                .padding(16.dp)
        )
        Text(
            text = "Shape Challenge Settings",
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onPageSelected(SettingsPage.SHAPE_CHALLENGE) }
                .padding(16.dp)
        )
        Text(
            text = "Color Settings",
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
    Text(text = "Word Challenge Settings Page")
}

@Composable
fun ShapeChallengeSettings() {
    Text(text = "Shape Challenge Settings Page")
}

@Composable
fun ColorSettings() {
    Text(text = "Color Settings Page")
}
