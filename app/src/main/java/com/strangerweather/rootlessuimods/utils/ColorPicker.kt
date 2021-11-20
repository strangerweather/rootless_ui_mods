package com.strangerweather.rootlessuimods.utils

import android.content.Context
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.unit.dp
import com.godaddy.android.colorpicker.ClassicColorPicker
import com.godaddy.android.colorpicker.HsvColor
import com.godaddy.android.colorpicker.toColorInt
import tk.zwander.fabricateoverlay.FabricatedOverlay
import tk.zwander.fabricateoverlay.FabricatedOverlayEntry
import tk.zwander.fabricateoverlay.OverlayAPI


var resourceValue = mutableStateOf(Int.MIN_VALUE)

@ExperimentalGraphicsApi
@Composable
fun ColorPicker() {
    ClassicColorPicker(
        modifier = Modifier
            .padding(20.dp)
            .aspectRatio(1f),
        onColorChanged = { color: HsvColor ->
            resourceValue.value = color.toColorInt()
            println("pickedColor = $color")
            println("convertedColor = ${resourceValue.value}")
        }
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