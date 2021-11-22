package com.strangerweather.rootlessuimods.components.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import com.strangerweather.rootlessuimods.utils.ColorCircle
import com.strangerweather.rootlessuimods.utils.ColorPicker

@ExperimentalGraphicsApi
@Composable
fun ColorPickerDialog(state: MutableState<Boolean>) {
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

