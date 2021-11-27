package com.strangerweather.rootlessuimods.components.dialogs

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.strangerweather.rootlessuimods.components.*
import com.strangerweather.rootlessuimods.ui.theme.Purple500
import com.strangerweather.rootlessuimods.utils.MainViewModel

@ExperimentalGraphicsApi
@Composable
fun ColorPickerDialog(
    state: MutableState<Boolean>,
    context: Context,
    name: String,
    target: String,
    overlay: String,
) {
    AlertDialog(modifier = Modifier.height(600.dp),
        onDismissRequest = { state.value = false },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ColorTextField()
                ColorPicker()
                Spacer(modifier = Modifier.height(20.dp))
                ColorCircle()
            }
        },
        buttons = {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
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
                        registerLayer(context, name, target, overlay)
                    }, Modifier.width(100.dp)
                ) {
                    Text("Confirm")
                }
            }
        }
    )
}

