package com.example.codebreaker.shape_challenge

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.codebreaker.ui.theme.CodeBreakerTheme

@Composable
fun ShapeChallengeScreen(
    viewModel: ShapeChallengeViewModel,
    modifier: Modifier = Modifier,
) {
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
            ShapeGrid(state = viewModel.uiState.value)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ShapeButtons(viewModel = viewModel)
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ShapeScreenPreview() {
    CodeBreakerTheme {
        ShapeChallengeScreen(
            viewModel = ShapeChallengeViewModel(
                ShapeChallengeState(
                    secretCode = listOf(Shape.TRIANGLE, Shape.PLUS, Shape.CIRCLE, Shape.CIRCLE),
                    guesses = listOf(
                        listOf(Shape.TRIANGLE, Shape.TRIANGLE, Shape.TRIANGLE, Shape.TRIANGLE),
                        listOf(Shape.TRIANGLE, Shape.SQUARE, Shape.PLUS, Shape.CIRCLE),
                    ),
                    currentGuess = listOf(Shape.PLUS, Shape.CRESCENT, Shape.STAR),
                )
            ),
        )
    }
}
