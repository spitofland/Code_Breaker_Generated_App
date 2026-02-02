package com.example.codebreaker.shape_challenge

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.codebreaker.ui.theme.CodeBreakerTheme

@Composable
fun ShapeChallengeScreen(
    uiState: ShapeChallengeState,
    addToGuess: (Shape) -> Unit,
    submitGuess: () -> Unit,
    removeLastFromGuess: () -> Unit,
    newGame: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(text = "Shape Code Breaker", fontSize = 8.em)
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShapeGrid(state = uiState)
        }
        if (uiState.isGameOver) {
            Text(
                "SUCCESS!",
                fontSize = 8.em,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )
            Button(onClick = newGame) {
                Text("New Game")
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
        )
    }
}
