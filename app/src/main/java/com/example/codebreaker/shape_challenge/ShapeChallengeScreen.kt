package com.example.codebreaker.shape_challenge

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.codebreaker.common.GameOverPrompt
import com.example.codebreaker.ui.theme.CodeBreakerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShapeChallengeScreen(
    uiState: ShapeChallengeState,
    addToGuess: (Shape) -> Unit,
    submitGuess: () -> Unit,
    removeLastFromGuess: () -> Unit,
    newGame: () -> Unit,
    back: () -> Unit,
    settings: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Shape Code Breaker") },
                navigationIcon = {
                    IconButton(onClick = back) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = settings) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ShapeGrid(state = uiState)
            }
            if (uiState.isGameOver) {
                GameOverPrompt(
                    won = uiState.isWin,
                    guesses = uiState.guesses.size,
                    newGame = newGame,
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        for (shape in uiState.secretCode) {
                            Image(
                                painter = painterResource(id = shape.drawable),
                                contentDescription = shape.toString(),
                                modifier = Modifier.padding(4.dp).size(40.dp)
                            )
                        }
                    }
                }
            } else {
                ShapeButtons(
                    addToGuess,
                    submitGuess,
                    removeLastFromGuess,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShapeScreenPreview() {
    CodeBreakerTheme {
        ShapeChallengeScreen(
            uiState = ShapeChallengeState(
                secretCode = listOf(Shape.TRIANGLE, Shape.PLUS, Shape.CIRCLE, Shape.CIRCLE),
                guesses = listOf(
                    listOf(Shape.TRIANGLE, Shape.TRIANGLE, Shape.TRIANGLE, Shape.TRIANGLE),
                    listOf(Shape.TRIANGLE, Shape.SQUARE, Shape.PLUS, Shape.CIRCLE),
                ),
                currentGuess = listOf(Shape.PLUS, Shape.CRESCENT, Shape.STAR),
            ),
            addToGuess = {},
            submitGuess = {},
            removeLastFromGuess = {},
            newGame = {},
            back = {},
            settings = {}
        )
    }
}
