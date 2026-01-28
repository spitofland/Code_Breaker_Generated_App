package com.example.codebreaker.shape_challenge

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codebreaker.R
import kotlinx.coroutines.launch
import kotlin.math.ceil
import kotlin.math.sqrt

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
            Row(
                modifier = Modifier.width(60.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "✓", color = Color.Green, fontSize = 20.sp)
                HintInfoButton(hint = "Shapes in correct positions")
            }
            Row(
                modifier = Modifier.width(60.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "–", color = Color(0xFFFFA500), fontSize = 20.sp)
                HintInfoButton(hint = "Correct shapes in wrong positions")
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
    val scope = rememberCoroutineScope()
    val tooltipState = rememberTooltipState()
    TooltipBox(
        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
        tooltip = { PlainTooltip { Text(hint) } },
        state = tooltipState,
    ) {
        IconButton(
            onClick = {
                scope.launch {
                    if (tooltipState.isVisible) {
                        tooltipState.dismiss()
                    } else {
                        tooltipState.show()
                    }
                }
            },
            modifier = Modifier
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
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        val numShapes = Shape.entries.count()
        val numColumns = ceil(sqrt(numShapes.toFloat())).toInt()

        Shape.entries.chunked(numColumns).forEach { row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                row.forEach { shape ->
                    Button(onClick = { /*TODO*/ }) {
                        Image(
                            painter = painterResource(id = shape.drawable),
                            contentDescription = shape.toString(),
                            modifier = Modifier.size(24.dp) // Adjust size as needed
                        )
                    }
                }
            }
        }
    }
}

enum class Shape(val drawable: Int) {
    CIRCLE(R.drawable.ic_circle),
    TRIANGLE(R.drawable.ic_triangle),
    SQUARE(R.drawable.ic_square),
    PLUS(R.drawable.ic_plus),
    CRESCENT(R.drawable.ic_crescent),
    STAR(R.drawable.ic_star),
    ;
}
