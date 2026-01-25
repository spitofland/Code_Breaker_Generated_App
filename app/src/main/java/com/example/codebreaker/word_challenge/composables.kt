package com.example.codebreaker.word_challenge

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameGrid(gameState: GameState, secretWord: String) {
    Column {
        // Previous guesses
        gameState.guesses.forEach { guess ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                guess.forEachIndexed { index, char ->
                    val (letterState, color) = when {
                        char == secretWord[index] -> LetterState.CORRECT to Color.Green
                        secretWord.contains(char) -> LetterState.WRONG_POSITION to Color.Yellow
                        else -> LetterState.NOT_IN_WORD to Color.Gray
                    }
                    GridBox(text = char.toString(), color = color)
                    if (index < 4) {
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
        }

        // Current guess
        if (gameState.guesses.size < 6) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                for (j in 0 until 5) {
                    val char = gameState.currentGuess.getOrNull(j)?.toString() ?: ""
                    GridBox(text = char, color = Color.Gray)
                    if (j < 4) {
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
        }

        // Empty rows
        for (i in 0 until (5 - gameState.guesses.size)) {
             Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                for (j in 0 until 5) {
                    GridBox(text = "", color = Color.Gray)
                    if (j < 4) {
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun GridBox(text: String, color: Color) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .border(2.dp, color),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, fontSize = 24.sp)
    }
}

@Composable
fun Keyboard(
    onLetterClick: (Char) -> Unit,
    onEnterClick: () -> Unit,
    onBackspaceClick: () -> Unit
) {
    val row1 = "QWERTYUIOP"
    val row2 = "ASDFGHJKL"
    val row3 = "ZXCVBNM"

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            row1.forEach { char ->
                KeyButton(char = char, onClick = { onLetterClick(char) }, modifier = Modifier.weight(1f))
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Spacer(modifier = Modifier.weight(0.5f))
            row2.forEach { char ->
                KeyButton(char = char, onClick = { onLetterClick(char) }, modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.weight(0.5f))
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Button(onClick = onEnterClick, modifier = Modifier.weight(1.5f)) { Text("Enter") }
            row3.forEach { char ->
                KeyButton(char = char, onClick = { onLetterClick(char) }, modifier = Modifier.weight(1f))
            }
            Button(onClick = onBackspaceClick, modifier = Modifier.weight(1.5f)) { Text("<") }
        }
    }
}

@Composable
fun KeyButton(char: Char, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = onClick, modifier = modifier.height(48.dp)) {
        Text(text = char.toString())
    }
}

enum class LetterState {
    CORRECT,
    WRONG_POSITION,
    NOT_IN_WORD
}