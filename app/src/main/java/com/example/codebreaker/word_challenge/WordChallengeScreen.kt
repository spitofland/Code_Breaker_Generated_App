package com.example.codebreaker.word_challenge

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.codebreaker.common.GameOverPrompt
import com.example.codebreaker.ui.theme.CodeBreakerTheme

@Composable
fun WordChallengeScreen(viewModel: WordChallengeViewModel) {
    val gameState by viewModel.gameState.collectAsState()
    WordChallengeContent(
        gameState = gameState,
        onLetterClick = viewModel::onLetterClick,
        onEnterClick = viewModel::onEnterClick,
        onBackspaceClick = viewModel::onBackspaceClick,
        newGame = viewModel::startGame,
    )
}

@Composable
private fun WordChallengeContent(
    gameState: GameState,
    onLetterClick: (Char) -> Unit,
    onEnterClick: () -> Unit,
    onBackspaceClick: () -> Unit,
    newGame: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = "Word Code Breaker", fontSize = 8.em)
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
        )
    }
}
