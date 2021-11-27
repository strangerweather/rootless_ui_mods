package com.strangerweather.rootlessuimods.components

import android.app.Application
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColor
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import com.godaddy.android.colorpicker.ClassicColorPicker
import com.godaddy.android.colorpicker.HsvColor
import com.godaddy.android.colorpicker.toColorInt
import com.strangerweather.rootlessuimods.ui.theme.Purple500
import com.strangerweather.rootlessuimods.utils.MainViewModel
import tk.zwander.fabricateoverlay.FabricatedOverlay
import tk.zwander.fabricateoverlay.FabricatedOverlayEntry
import tk.zwander.fabricateoverlay.OverlayAPI
import kotlin.math.absoluteValue


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
    val viewModel: MainViewModel = viewModel()
    val hexValue = viewModel.convertedHex.observeAsState()
    Box(
        modifier = Modifier
            .padding(10.dp)
            .size(60.dp)
            .background(
                color = if (hexValue.value == null) Color(resourceValue.value) else Color(
                    hexValue.value!!
                ), shape = CircleShape
            )
    )
}

fun registerLayer(context: Context, name: String, target: String, overlay: String) {
    val overlayEntries = listOf(
        FabricatedOverlayEntry(
            resourceName = "$target:$overlay",
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