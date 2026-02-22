package com.example.codebreaker.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.codebreaker.viewmodel.ColorSettingsViewModel

@Composable
fun ColorPicker(
    title: String,
    initialColor: Color,
    viewModel: ColorSettingsViewModel,
    onColorSelected: (Color) -> Unit
) = ColorPicker(
    title,
    initialColor,
    viewModel.backgroundColor.collectAsState().value ?: Color.White,
    viewModel.foregroundColor.collectAsState().value ?: Color.Black,
    onColorSelected,
)

@Composable
fun ColorPicker(
    title: String,
    initialColor: Color,
    backgroundColor: Color,
    foregroundColor: Color,
    onColorSelected: (Color) -> Unit
) {
    var color by remember { mutableStateOf(initialColor) }

    LaunchedEffect(color) {
        onColorSelected(color)
    }

    Column(modifier = Modifier.padding(bottom = 16.dp).background(color = backgroundColor)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = title, modifier = Modifier.weight(1f), color = foregroundColor)
            Box(
                modifier = Modifier
                    .width(80.dp)
                    .height(40.dp)
                    .background(color, RoundedCornerShape(25))
                    .border(2.dp, foregroundColor, RoundedCornerShape(25))
            )
        }
        Slider(
            value = color.red,
            onValueChange = { color = color.copy(red = it) },
            colors = SliderDefaults.colors(activeTrackColor = Color.Red, thumbColor = Color.Red),
        )
        Slider(
            value = color.green,
            onValueChange = { color = color.copy(green = it) },
            colors = SliderDefaults.colors(activeTrackColor = Color.Green, thumbColor = Color.Green),
        )
        Slider(
            value = color.blue,
            onValueChange = { color = color.copy(blue = it) },
            colors = SliderDefaults.colors(activeTrackColor = Color.Blue, thumbColor = Color.Blue),
        )
    }
}

@Preview
@Composable
fun ColorPickerPreview() {
    ColorPicker(
        "Preview Color",
        Color(0xAA, 0x11, 0x88),
        Color.Gray,
        Color.Black,
    ) { }
}