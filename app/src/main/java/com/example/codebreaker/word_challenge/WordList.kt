package com.example.codebreaker.word_challenge

import android.content.Context
import java.util.Locale

class WordList(context: Context) {
    companion object {
        private val fiveLetters = "[A-Z]{5}".toRegex()
    }

    val words: List<String> = context.assets
            .open("words.txt")
            .bufferedReader()
            .readLines()
            .map { it.uppercase(Locale.US) }
            .filter { fiveLetters.matches(it) }
}
