package com.example.codebreaker.word_challenge

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.example.codebreaker.settings.SettingsActivity
import com.example.codebreaker.ui.theme.CodeBreakerTheme

class WordChallengeActivity : ComponentActivity() {
    private val viewModel: WordChallengeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        viewModel.startGame()
        setContent {
            CodeBreakerTheme {
                // A surface container using the 'background' color from the theme
                WordChallengeScreen(
                    viewModel = viewModel,
                    back = { finish() },
                    settings = { startActivity(Intent(this, SettingsActivity::class.java)) }
                )
            }
        }
    }
}
