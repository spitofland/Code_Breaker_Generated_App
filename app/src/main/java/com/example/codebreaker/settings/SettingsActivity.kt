package com.example.codebreaker.settings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.codebreaker.ui.theme.CodeBreakerTheme

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodeBreakerTheme {
                SettingsScreen(back = {
                    finish()
                })
            }
        }
    }
}
