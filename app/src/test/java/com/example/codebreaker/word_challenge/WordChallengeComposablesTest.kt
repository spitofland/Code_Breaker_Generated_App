package com.example.codebreaker.word_challenge

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WordChallengeComposablesTest {

    @Test
    fun checkGuess_noMatches() {
        val result = checkGuess("GUESS", "ABCDF")
        assertThat(result).containsExactly(
            'G' to LetterState.NOT_IN_WORD,
            'U' to LetterState.NOT_IN_WORD,
            'E' to LetterState.NOT_IN_WORD,
            'S' to LetterState.NOT_IN_WORD,
            'S' to LetterState.NOT_IN_WORD,
        )
    }

    @Test
    fun checkGuess_allCorrect() {
        val result = checkGuess("GUESS", "GUESS")
        assertThat(result).containsExactly(
            'G' to LetterState.CORRECT,
            'U' to LetterState.CORRECT,
            'E' to LetterState.CORRECT,
            'S' to LetterState.CORRECT,
            'S' to LetterState.CORRECT,
        )
    }

    @Test
    fun checkGuess_someWrongPosition() {
        val result = checkGuess("GUESS", "SEUGS")
        assertThat(result).containsExactly(
            'G' to LetterState.WRONG_POSITION,
            'U' to LetterState.WRONG_POSITION,
            'E' to LetterState.WRONG_POSITION,
            'S' to LetterState.WRONG_POSITION,
            'S' to LetterState.CORRECT,
        )
    }

    @Test
    fun checkGuess_mixed() {
        val result = checkGuess("GUESS", "GUSTS")
        assertThat(result).containsExactly(
            'G' to LetterState.CORRECT,
            'U' to LetterState.CORRECT,
            'E' to LetterState.NOT_IN_WORD,
            'S' to LetterState.WRONG_POSITION,
            'S' to LetterState.CORRECT,
        )
    }

    @Test
    fun checkGuess_duplicates() {
        val result = checkGuess("BOBBY", "HOBBY")
        assertThat(result).containsExactly(
            'B' to LetterState.NOT_IN_WORD,
            'O' to LetterState.CORRECT,
            'B' to LetterState.CORRECT,
            'B' to LetterState.CORRECT,
            'Y' to LetterState.CORRECT,
        )
    }
}
