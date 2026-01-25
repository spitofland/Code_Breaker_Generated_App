package com.example.codebreaker.word_challenge

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codebreaker.ui.theme.CodeBreakerTheme

@Composable
fun WordChallengeScreen(viewModel: WordChallengeViewModel) {
    val gameState by viewModel.gameState.collectAsState()
    WordChallengeContent(
        gameState = gameState,
        onLetterClick = viewModel::onLetterClick,
        onEnterClick = viewModel::onEnterClick,
        onBackspaceClick = viewModel::onBackspaceClick
    )
}

@Composable
private fun WordChallengeContent(
    gameState: GameState,
    onLetterClick: (Char) -> Unit,
    onEnterClick: () -> Unit,
    onBackspaceClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Code Breaker", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        GameGrid(gameState = gameState, secretWord = gameState.secretWord)
        Spacer(modifier = Modifier.height(16.dp))
        Keyboard(
            onLetterClick = onLetterClick,
            onEnterClick = onEnterClick,
            onBackspaceClick = onBackspaceClick
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WordChallengeScreenPreview() {
    CodeBreakerTheme {
        WordChallengeContent(
            gameState = GameState(
                guesses = listOf("GHOST"),
                currentGuess = "APP",
                secretWord = "APPLE"
            ),
            onLetterClick = {},
            onEnterClick = {},
            onBackspaceClick = {}
        )
    }
}
