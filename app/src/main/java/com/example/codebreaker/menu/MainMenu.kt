package com.example.codebreaker.menu

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.codebreaker.R
import com.example.codebreaker.settings.SettingsActivity
import com.example.codebreaker.word_challenge.WordChallengeActivity
import com.example.codebreaker.shape_challenge.ShapeChallengeActivity
import com.example.codebreaker.ui.theme.CodeBreakerTheme

@Composable
fun MainMenu(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = stringResource(R.string.app_name), fontSize = 8.em)
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                stringResource(R.string.main_menu_start_new),
                fontSize = 10.em,
                fontWeight = FontWeight.Bold,
            )
            Button(onClick = {
                context.startActivity(Intent(context, WordChallengeActivity::class.java))
            }) {
                Text(stringResource(R.string.word_game_title), fontSize = 6.em)
            }
            Button(onClick = {
                context.startActivity(Intent(context, ShapeChallengeActivity::class.java))
            }) {
                Text(stringResource(R.string.shape_game_title), fontSize = 6.em)
            }
        }
        Button(onClick = { context.startActivity(Intent(context, SettingsActivity::class.java)) }) {
            Icon(
                Icons.Default.Settings,
                contentDescription = stringResource(R.string.settings_title),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainMenuPreview() {
    CodeBreakerTheme {
        MainMenu()
    }
}
