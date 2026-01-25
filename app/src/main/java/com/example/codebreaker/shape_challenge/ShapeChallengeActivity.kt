package com.example.codebreaker.shape_challenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.codebreaker.ui.theme.CodeBreakerTheme

class ShapeChallengeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CodeBreakerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ShapeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
