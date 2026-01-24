package com.example.codebreaker.shapes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PlainTooltipBox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            ShapeGrid()
        }
        Spacer(modifier = Modifier.height(16.dp))
        ShapeButtons()
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShapeGrid() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        // Header Row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Spacers for alignment with grid
            repeat(4) {
                Spacer(modifier = Modifier.width(60.dp))
            }

            // Headers for feedback columns
            Box(
                modifier = Modifier.width(30.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "✓", color = Color.Green, fontSize = 20.sp)
                    HintInfoButton(hint = "Shapes in correct positions")
                }
            }
            Box(
                modifier = Modifier.width(30.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "–", color = Color(0xFFFFA500), fontSize = 20.sp)
                    HintInfoButton(hint = "Correct shapes in wrong positions")
                }
            }
        }

        // Grid Rows
        for (i in 0 until 12) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Shape Boxes
                repeat(4) {
                    GridBox(text = "", color = Color.Gray)
                }
                // Feedback Boxes
                FeedbackBox()
                FeedbackBox()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HintInfoButton(hint: String) {
    PlainTooltipBox(
        tooltip = { Text(hint) }
    ) {
        IconButton(
            onClick = { /* Clicks on info buttons are not handled */ },
            modifier = Modifier
                .tooltipAnchor()
                .size(20.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Hint",
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun FeedbackBox() {
    Box(
        modifier = Modifier
            .size(30.dp)
            .border(1.dp, Color.DarkGray)
    )
}

@Composable
fun GridBox(text: String, color: Color) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .border(2.dp, color),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, fontSize = 24.sp)
    }
}

@Composable
fun ShapeButtons() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
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
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
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
