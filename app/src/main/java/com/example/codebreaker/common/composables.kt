package com.example.codebreaker.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

@Composable
fun GameOverPrompt(
    won: Boolean,
    guesses: Int,
    newGame: () -> Unit,
    secret: @Composable (ColumnScope.() -> Unit),
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (won) {
            Text(
                "SUCCESS!",
                fontSize = 8.em,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
            )
            Text(
                "$guesses Guesses",
                fontSize = 6.em,
                fontWeight = FontWeight.Bold,
            )
        } else {
            Text(
                "GAME OVER",
                fontSize = 8.em,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
            )
            Text(
                "The secret was:",
                fontSize = 4.em,
                fontWeight = FontWeight.Bold,
            )
            secret()
        }
    }
    Button(onClick = newGame) {
        Text("New Game")
    }
}