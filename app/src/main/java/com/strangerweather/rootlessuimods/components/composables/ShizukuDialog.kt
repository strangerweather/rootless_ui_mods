package com.strangerweather.rootlessuimods.components.composables

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.strangerweather.rootlessuimods.content.HomeScreen


@Composable
fun ShizukuDialog() {
    val openDialog = remember { mutableStateOf(true) }

    val activity = (LocalContext.current as? Activity)

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { },
            title = { },
            text = {
                HomeScreen()
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { activity?.finish() }
                    ) {
                        Text("Dismiss")
                    }
                }
            }
        )
    }
}