package com.example.codebreaker.word_challenge

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.codebreaker.R
import com.example.codebreaker.common.GameOverPrompt
import com.example.codebreaker.ui.theme.CodeBreakerTheme

@Composable
fun WordChallengeScreen(
    viewModel: WordChallengeViewModel,
    back: () -> Unit,
    settings: () -> Unit,
) {
    val gameState by viewModel.gameState.collectAsState()
    WordChallengeContent(
        gameState = gameState,
        onLetterClick = viewModel::onLetterClick,
        onEnterClick = viewModel::onEnterClick,
        onBackspaceClick = viewModel::onBackspaceClick,
        newGame = viewModel::startGame,
        back = back,
        settings = settings
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WordChallengeContent(
    gameState: GameState,
    onLetterClick: (Char) -> Unit,
    onEnterClick: () -> Unit,
    onBackspaceClick: () -> Unit,
    newGame: () -> Unit,
    back: () -> Unit,
    settings: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.word_game_title)) },
                navigationIcon = {
                    IconButton(onClick = back) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.navigation_go_back),
                        )
                    }
                },
                actions = {
                    IconButton(onClick = settings) {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = stringResource(R.string.settings_title),
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            GameGrid(gameState = gameState)
            if (gameState.isGameOver) {
                GameOverPrompt(
                    won = gameState.isWin,
                    guesses = gameState.guesses.size,
                    newGame = newGame,
                ) {
                    Text(
                        text = gameState.secretWord,
                        fontSize = 8.em,
                        fontWeight = FontWeight.Bold,
                    )
                }
            } else {
                Keyboard(
                    gameState = gameState,
                    onLetterClick = onLetterClick,
                    onEnterClick = onEnterClick,
                    onBackspaceClick = onBackspaceClick,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WordChallengeScreenPreview() {
    CodeBreakerTheme {
        WordChallengeContent(
            gameState = GameState(
                guesses = listOf("SPELL"),
                currentGuess = "APP",
                secretWord = "APPLE"
            ),
            onLetterClick = {},
            onEnterClick = {},
            onBackspaceClick = {},
            newGame = {},
            back = {},
            settings = {}
        )
    }
}
