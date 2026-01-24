package com.example.codebreaker.shapes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.codebreaker.ui.theme.CodeBreakerTheme

class ShapeActivity : ComponentActivity() {
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

@Composable
fun ShapeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text("circle")
            }
            Button(onClick = { /*TODO*/ }) {
                Text("triangle")
            }
            Button(onClick = { /*TODO*/ }) {
                Text("square")
            }
            Button(onClick = { /*TODO*/ }) {
                Text("plus")
            }
            Button(onClick = { /*TODO*/ }) {
                Text("crescent")
            }
            Button(onClick = { /*TODO*/ }) {
                Text("star")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShapeScreenPreview() {
    CodeBreakerTheme {
        ShapeScreen()
    }
}
