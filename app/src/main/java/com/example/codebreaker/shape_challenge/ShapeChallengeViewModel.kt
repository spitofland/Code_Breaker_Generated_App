package com.example.codebreaker.shape_challenge

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ShapeChallengeViewModel(initialState: ShapeChallengeState = ShapeChallengeState()) : ViewModel() {

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<ShapeChallengeState> = _uiState.asStateFlow()

    init {
        generateSecretCode()
    }

    private fun generateSecretCode() {
        val secret = List(4) { Shape.entries.random() }
        _uiState.update { it.copy(secretCode = secret) }
    }

    fun addToGuess(shape: Shape) {
        if (_uiState.value.currentGuess.size < 4) {
            _uiState.update {
                val newGuess = it.currentGuess + shape
                it.copy(currentGuess = newGuess)
            }
        }
    }

    fun removeLastFromGuess() {
        if (_uiState.value.currentGuess.isNotEmpty()) {
            _uiState.update {
                val newGuess = it.currentGuess.dropLast(1)
                it.copy(currentGuess = newGuess)
            }
        }
    }

    fun submitGuess() {
        if (_uiState.value.currentGuess.size == 4) {
            // Logic to check guess will be added here
            _uiState.update {
                val newGuesses = it.guesses + listOf(it.currentGuess)
                it.copy(guesses = newGuesses, currentGuess = emptyList())
            }
        }
    }
}

data class ShapeChallengeState(
    val secretCode: List<Shape> = emptyList(),
    val guesses: List<List<Shape>> = emptyList(),
    val currentGuess: List<Shape> = emptyList()
)
