package com.strangerweather.rootlessuimods.components.composables

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.strangerweather.rootlessuimods.R
import com.strangerweather.rootlessuimods.components.pickers.ColorPickerDialog

@ExperimentalGraphicsApi
@Composable
fun TopCardColorMods(
    context: Context,
    name: String,
    target: String,
    overlay: String
) {
    val showAlertDialog = remember { mutableStateOf(false) }

    if (showAlertDialog.value) {
        AlertDialogView(
            state = showAlertDialog,
            context = context,
            name = name,
            target = target,
            overlay = overlay
        )
    }
    Column(
        Modifier.
    background(if (isSystemInDarkTheme()) Color.DarkGray else Color(0xFFF5F5F5))) {
        Card(
            Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(10.dp), elevation = 7.dp
        ) {
            Column(
                Modifier
                    .padding(15.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedButton(
                        onClick = { showAlertDialog.value = true },
                        Modifier
                            .height(50.dp)
                            .width(135.dp),
                    ) {
                        Text(text = stringResource(id = R.string.color_picker))
                    }
                }
            }
        }
    }
}


@ExperimentalGraphicsApi
@Composable
fun AlertDialogView(
    state: MutableState<Boolean>,
    context: Context,
    name: String,
    target: String,
    overlay: String
) {
    ColorPickerDialog(state, context, name, target, overlay)
}
