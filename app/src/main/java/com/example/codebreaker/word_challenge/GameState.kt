package com.example.codebreaker.word_challenge

data class GameState(
    val guesses: List<String> = emptyList(),
    val currentGuess: String = "",
    val secretWord: String = "",
    val isGameOver: Boolean = false,
    val hasWon: Boolean = false
)
