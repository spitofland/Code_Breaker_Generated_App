package com.example.codebreaker.menu

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.codebreaker.word_challenge.WordChallengeActivity
import com.example.codebreaker.shape_challenge.ShapeChallengeActivity
import com.example.codebreaker.ui.theme.CodeBreakerTheme

@Composable
fun MainMenu(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Shape Code Breaker", fontSize = 8.em)
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                "Start New:",
                fontSize = 10.em,
                fontWeight = FontWeight.Bold,
            )
            Button(onClick = {
                context.startActivity(Intent(context, WordChallengeActivity::class.java))
            }) {
                Text("Word Challenge", fontSize = 6.em)
            }
            Button(onClick = {
                context.startActivity(Intent(context, ShapeChallengeActivity::class.java))
            }) {
                Text("Shape Challenge", fontSize = 6.em)
            }
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
