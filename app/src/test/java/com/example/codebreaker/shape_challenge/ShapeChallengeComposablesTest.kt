package com.example.codebreaker.shape_challenge

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ShapeChallengeComposablesTest {

    @Test
    fun calculateFeedback_noMatches() {
        val secret = listOf(Shape.CIRCLE, Shape.SQUARE, Shape.TRIANGLE, Shape.PLUS)
        val guess = listOf(Shape.STAR, Shape.CRESCENT, Shape.STAR, Shape.CRESCENT)
        val (correct, wrongPosition) = calculateFeedback(guess, secret)
        assertThat(correct).isEqualTo(0)
        assertThat(wrongPosition).isEqualTo(0)
    }

    @Test
    fun calculateFeedback_allCorrect() {
        val secret = listOf(Shape.CIRCLE, Shape.SQUARE, Shape.TRIANGLE, Shape.PLUS)
        val guess = listOf(Shape.CIRCLE, Shape.SQUARE, Shape.TRIANGLE, Shape.PLUS)
        val (correct, wrongPosition) = calculateFeedback(guess, secret)
        assertThat(correct).isEqualTo(4)
        assertThat(wrongPosition).isEqualTo(0)
    }

    @Test
    fun calculateFeedback_someWrongPosition() {
        val secret = listOf(Shape.CIRCLE, Shape.SQUARE, Shape.TRIANGLE, Shape.PLUS)
        val guess = listOf(Shape.SQUARE, Shape.CIRCLE, Shape.PLUS, Shape.TRIANGLE)
        val (correct, wrongPosition) = calculateFeedback(guess, secret)
        assertThat(correct).isEqualTo(0)
        assertThat(wrongPosition).isEqualTo(4)
    }

    @Test
    fun calculateFeedback_mixed() {
        val secret = listOf(Shape.CIRCLE, Shape.SQUARE, Shape.TRIANGLE, Shape.PLUS)
        val guess = listOf(Shape.CIRCLE, Shape.TRIANGLE, Shape.SQUARE, Shape.STAR)
        val (correct, wrongPosition) = calculateFeedback(guess, secret)
        assertThat(correct).isEqualTo(1)
        assertThat(wrongPosition).isEqualTo(2)
    }

    @Test
    fun calculateFeedback_duplicates() {
        val secret = listOf(Shape.CIRCLE, Shape.CIRCLE, Shape.TRIANGLE, Shape.PLUS)
        val guess = listOf(Shape.CIRCLE, Shape.SQUARE, Shape.CIRCLE, Shape.TRIANGLE)
        val (correct, wrongPosition) = calculateFeedback(guess, secret)
        assertThat(correct).isEqualTo(1)
        assertThat(wrongPosition).isEqualTo(2)
    }
}
