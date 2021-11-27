package com.strangerweather.rootlessuimods.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.godaddy.android.colorpicker.ClassicColorPicker
import com.godaddy.android.colorpicker.HsvColor
import com.godaddy.android.colorpicker.toColorInt
import com.strangerweather.rootlessuimods.ui.theme.Purple500


var resourceValue = mutableStateOf(Purple500.toArgb())

@ExperimentalGraphicsApi
@Composable
fun ColorPicker() {
    ClassicColorPicker(
        modifier = Modifier
            .aspectRatio(1f),
        onColorChanged = { color: HsvColor ->
            resourceValue.value = color.toColorInt()
        },
        color = Color(resourceValue.value),
    )
}

@Composable
fun ColorCircle() {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .size(60.dp)
            .background(
                color = Color(resourceValue.value),
                shape = CircleShape
            )
    )
}