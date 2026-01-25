package com.example.codebreaker.menu

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.codebreaker.word_challenge.WordChallengeActivity
import com.example.codebreaker.shape_challenge.ShapeChallengeActivity
import com.example.codebreaker.ui.theme.CodeBreakerTheme

@Composable
fun MainMenu(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Start New:")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            context.startActivity(Intent(context, WordChallengeActivity::class.java))
        }) {
            Text("Word Challenge")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            context.startActivity(Intent(context, ShapeChallengeActivity::class.java))
        }) {
            Text("Shape Challenge")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainMenuPreview() {
    CodeBreakerTheme {
        MainMenu()
    }
}
