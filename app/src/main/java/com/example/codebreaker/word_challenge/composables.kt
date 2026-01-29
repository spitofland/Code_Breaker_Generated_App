package com.example.codebreaker.word_challenge

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.annotations.VisibleForTesting

@Composable
fun GameGrid(gameState: GameState) {
    Column {
        // Previous guesses
        gameState.guesses.forEach { guess ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                checkGuess(guess, gameState.secretWord).forEachIndexed { index, char ->
                    GridBox(text = char.first.toString(), state = char.second)
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
                    GridBox(text = char, state = LetterState.NOT_GRADED)
                    if (j < 4) {
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
        }

        // Empty rows
        repeat(5 - gameState.guesses.size) {
             Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                for (j in 0 until 5) {
                    GridBox(text = "", state = LetterState.NOT_GRADED)
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
fun GridBox(text: String, state: LetterState) {
    val colors = getColors(letterState = state)
    Box(
        modifier = Modifier
            .size(60.dp)
            .background(colors.background)
            .border(2.dp, colors.border),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, fontSize = 24.sp, color = colors.text)
    }
}

private const val BACKSPACE = "Backspace"
private const val SUBMIT_GUESS = "Submit"

@Composable
fun Keyboard(
    gameState: GameState,
    onLetterClick: (Char) -> Unit,
    onEnterClick: () -> Unit,
    onBackspaceClick: () -> Unit
) {
    val letterStates = gameState.guesses
        .flatMap { checkGuess(it, gameState.secretWord) }
        .groupBy(keySelector = { it.first.toString() }, valueTransform = { it.second })
        .mapValues { (_, states) ->
            states.minOfWith(compareBy { state ->
                when (state) {
                    LetterState.CORRECT -> 0
                    LetterState.WRONG_POSITION -> 1
                    LetterState.NOT_IN_WORD -> 2
                    LetterState.NOT_GRADED -> 3
                }
            }) { it }
        }.toMutableMap()
    letterStates[BACKSPACE] =
        if (gameState.currentGuess.isEmpty()) LetterState.NOT_IN_WORD else LetterState.NOT_GRADED
    letterStates[SUBMIT_GUESS] =
        if (gameState.currentGuess.length < 5 || !gameState.isCurrentGuessValidWord) {
            LetterState.NOT_IN_WORD
        } else {
            LetterState.NOT_GRADED
        }

    val rows =
        listOf(
            "Q,W,E,R,T,Y,U,I,O,P",
            "A,S,D,F,G,H,J,K,L",
            "$BACKSPACE,Z,X,C,V,B,N,M,$SUBMIT_GUESS",
        ).map { it.split(",") }
            .map {
                it to it.map { char ->
                    if (char.length == 1) 1f else 1.5f
                }.sum()
            }
    val maxRowLength = rows.maxOf { it.second }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        rows.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                if (row.second < maxRowLength) {
                    Spacer(modifier = Modifier.weight((maxRowLength - row.second) / 2.0f))
                }
                row.first.forEach { char ->
                    val (onClick, label) =
                        when (char) {
                            BACKSPACE -> onBackspaceClick to "Del"
                            SUBMIT_GUESS -> onEnterClick to ">>"
                            else -> fun() { onLetterClick(char.first()) } to char
                        }
                    KeyButton(
                        char = label,
                        onClick = onClick,
                        letterState = letterStates[char],
                        modifier = Modifier.weight(if (char.length == 1) 1f else 1.5f),
                    )
                }
                if (row.second < maxRowLength) {
                    Spacer(modifier = Modifier.weight((maxRowLength - row.second) / 2.0f))
                }
            }
        }
    }
}

@Composable
fun KeyButton(char: String, onClick: () -> Unit, letterState: LetterState?, modifier: Modifier = Modifier) {
    val colors = getColors(letterState ?: LetterState.NOT_GRADED)
    Button(
        onClick = onClick,
        modifier = modifier.height(48.dp),
        contentPadding = PaddingValues(8.dp, 8.dp, 8.dp, 8.dp),
        border = BorderStroke(2.dp, colors.border),
        colors = ButtonColors(
            colors.background,
            colors.background,
            colors.background,
            colors.background
        ),
    ) {
        Text(text = char, color = colors.text)
    }
}

enum class LetterState {
    CORRECT,
    WRONG_POSITION,
    NOT_IN_WORD,
    NOT_GRADED,
}

data class LetterColors(
    val text: Color,
    val background: Color,
    val border: Color,
)

fun getColors(letterState: LetterState): LetterColors =
    when (letterState) {
        LetterState.CORRECT -> LetterColors(Color.Black, Color.Green, Color.Green)
        LetterState.WRONG_POSITION -> LetterColors(Color.Black, Color.Yellow, Color.Yellow)
        LetterState.NOT_IN_WORD -> LetterColors(Color.Black, Color.Gray, Color.Gray)
        LetterState.NOT_GRADED -> LetterColors(Color.Black, Color.Transparent, Color.Gray)
    }

@VisibleForTesting
internal fun checkGuess(guess: String, secretWord: String): List<Pair<Char, LetterState>> {
    val unmatchedSecret = secretWord.filterIndexed { index, char ->
        guess[index] != char
    }.toMutableList()

    return guess.mapIndexed { index, char ->
        val letterState = when {
            char == secretWord[index] -> LetterState.CORRECT
            unmatchedSecret.contains(char) -> {
                unmatchedSecret.remove(char) // Prevent double reporting
                LetterState.WRONG_POSITION
            }
            else -> LetterState.NOT_IN_WORD
        }
        Pair(char, letterState)
    }
}
