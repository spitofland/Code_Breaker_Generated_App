package com.example.codebreaker.shape_challenge

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.core.view.WindowCompat
import com.example.codebreaker.settings.SettingsActivity
import com.example.codebreaker.ui.theme.CodeBreakerTheme

class ShapeChallengeActivity : ComponentActivity() {
    private val viewModel: ShapeChallengeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContent {
            CodeBreakerTheme {
                val uiState = viewModel.uiState.collectAsState().value
                ShapeChallengeScreen(
                    uiState = uiState,
                    addToGuess = viewModel::addToGuess,
                    submitGuess = viewModel::submitGuess,
                    removeLastFromGuess = viewModel::removeLastFromGuess,
                    newGame = viewModel::newGame,
                    back = { finish() },
                    settings = { startActivity(Intent(this, SettingsActivity::class.java)) }
                )
            }
        }
    }
}
