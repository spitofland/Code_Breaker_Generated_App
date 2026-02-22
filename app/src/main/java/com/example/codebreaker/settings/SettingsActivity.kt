package com.example.codebreaker.settings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.example.codebreaker.ui.theme.CodeBreakerTheme

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContent {
            CodeBreakerTheme {
                SettingsScreen(back = {
                    finish()
                })
            }
        }
    }
}
