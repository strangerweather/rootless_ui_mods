package com.strangerweather.rootlessuimods.components.pickers

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.unit.dp
import com.strangerweather.rootlessuimods.functions.registerLayer

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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp, bottom = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ColorTextField(context, name, target, overlay)
                ColorPicker()
                ColorCircle()
            }
        },
        buttons = {
            Row(
                Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(
                    onClick = {
                        state.value = false
                    }, Modifier.width(100.dp)
                ) {
                    Text("Cancel")
                }
                Button(
                    onClick = {
                        state.value = false
                        registerLayer(context, name, target, overlay, 28, resourceValue.value)
                    }, Modifier.width(100.dp)
                ) {
                    Text("Confirm")
                }
            }
        }
    )
}


