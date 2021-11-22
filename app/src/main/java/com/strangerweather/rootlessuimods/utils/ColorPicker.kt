package com.strangerweather.rootlessuimods.utils

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.core.graphics.toColor
import com.godaddy.android.colorpicker.ClassicColorPicker
import com.godaddy.android.colorpicker.HsvColor
import com.godaddy.android.colorpicker.toColorInt
import com.strangerweather.rootlessuimods.ui.theme.Purple500
import tk.zwander.fabricateoverlay.FabricatedOverlay
import tk.zwander.fabricateoverlay.FabricatedOverlayEntry
import tk.zwander.fabricateoverlay.OverlayAPI


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
        color = Color(resourceValue.value)
    )
}

@Composable
fun ColorCircle() {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .size(60.dp)
            .background(color = Color(resourceValue.value), shape = CircleShape)
    )
}

fun registerLayer(context: Context, name: String, target: String) {
    val overlayEntries = listOf(
        FabricatedOverlayEntry(
            resourceName = "$target:color/system_accent1_100",
            resourceType = 28,
            resourceValue = resourceValue.value
        )
    )
    OverlayAPI.getInstance(context) { api ->
        api.registerFabricatedOverlay(
            FabricatedOverlay(
                "com.strangerweather.rootlessuimods.$target.$name",
                target
            ).apply {
                overlayEntries.forEach { overlay ->
                    entries[overlay.resourceName] = overlay
                }
            }
        )
    }
}