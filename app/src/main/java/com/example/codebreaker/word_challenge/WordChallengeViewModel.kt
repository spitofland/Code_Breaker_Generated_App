package com.example.codebreaker.word_challenge

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class WordChallengeViewModel(application: Application) : AndroidViewModel(application) {
    private val wordList = WordList(application)

    private val _gameState = MutableStateFlow(GameState())
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()

    fun startGame() {
        val secretWord = wordList.getWords().random().uppercase()
        _gameState.value = GameState(secretWord = secretWord)
    }

    fun onLetterClick(letter: Char) {
        if (_gameState.value.currentGuess.length < 5) {
            _gameState.update { currentState ->
                currentState.copy(currentGuess = currentState.currentGuess + letter)
            }
        }
    }

    fun onEnterClick() {
        if (_gameState.value.currentGuess.length == 5) {
            if (wordList.getWords().contains(_gameState.value.currentGuess.lowercase())) {
                _gameState.update { currentState ->
                    val newGuesses = currentState.guesses + currentState.currentGuess
                    currentState.copy(
                        guesses = newGuesses,
                        currentGuess = ""
                    )
                }
            } else {
                // Word not in list, clear current guess
                _gameState.update { currentState ->
                    currentState.copy(currentGuess = "")
                }
            }
        }
    }

    fun onBackspaceClick() {
        if (_gameState.value.currentGuess.isNotEmpty()) {
            _gameState.update { currentState ->
                currentState.copy(currentGuess = currentState.currentGuess.dropLast(1))
            }
        }
    }
}
