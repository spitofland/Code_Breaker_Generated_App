package com.example.codebreaker.word_challenge

data class GameState(
    val guesses: List<String> = emptyList(),
    val currentGuess: String = "",
    val secretWord: String = "",
    val isCurrentGuessValidWord: Boolean = false,
) {
    val isGameOver: Boolean
        get() = guesses.lastOrNull() == secretWord
}
