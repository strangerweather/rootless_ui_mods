package com.strangerweather.rootlessuimods.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ColorPickerDialog(){
    val openDialog = remember { mutableStateOf(true) }
    var text by remember { mutableStateOf("") }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "Title")
            },
            text = {
                Column() {
                    TextField(
                        value = text,
                        onValueChange = { text = it }
                    )
                    Text("Custom Text")
                    Checkbox(checked = false, onCheckedChange = {})
                }
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { openDialog.value = false }
                    ) {
                        Text("Dismiss")
                    }
                }
            }
        )
    }
}