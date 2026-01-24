package com.example.codebreaker.game

import android.content.Context

class WordList(private val context: Context) {

    fun getWords(): List<String> {
        return context.assets.open("words.txt").bufferedReader().readLines()
    }
}
