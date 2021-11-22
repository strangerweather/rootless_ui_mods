package com.strangerweather.rootlessuimods.components.dialogs

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.platform.LocalContext
import com.strangerweather.rootlessuimods.utils.ColorCircle
import com.strangerweather.rootlessuimods.utils.ColorPicker
import com.strangerweather.rootlessuimods.utils.registerLayer

@ExperimentalGraphicsApi
@Composable
fun ColorPickerDialog(
    state: MutableState<Boolean>,
    context: Context,
    name: String,
    target: String,
    overlay: String,
) {
    AlertDialog(
        onDismissRequest = { state.value = false },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ColorPicker()
                ColorCircle()
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    state.value = false
                    registerLayer(context, name, target, overlay)
                }) {
                Text("Confirm")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    state.value = false
                }) {
                Text("Cancel")
            }
        }
    )
}

