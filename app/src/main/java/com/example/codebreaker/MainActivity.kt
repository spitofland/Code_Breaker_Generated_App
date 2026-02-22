package com.example.codebreaker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.example.codebreaker.menu.MainMenu
import com.example.codebreaker.ui.theme.CodeBreakerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContent {
            CodeBreakerTheme {
                Scaffold { innerPadding ->
                    MainMenu(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
