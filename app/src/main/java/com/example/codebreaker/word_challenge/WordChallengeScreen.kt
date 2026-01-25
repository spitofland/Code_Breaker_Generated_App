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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WordChallengeScreen(viewModel: WordChallengeViewModel) {
    val gameState by viewModel.gameState.collectAsState()

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
            onLetterClick = { viewModel.onLetterClick(it) },
            onEnterClick = { viewModel.onEnterClick() },
            onBackspaceClick = { viewModel.onBackspaceClick() }
        )
    }
}
