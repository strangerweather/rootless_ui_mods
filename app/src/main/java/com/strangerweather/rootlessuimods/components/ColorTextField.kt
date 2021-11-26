package com.strangerweather.rootlessuimods.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.strangerweather.rootlessuimods.R

@Composable
fun ColorTextField() {
    Column(
        Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        var text by remember { mutableStateOf("#") }
        OutlinedTextField(value = text, onValueChange = { colorCode ->
            text = colorCode
        },
            label = { Text(text = "Enter Hex Value") }, singleLine = true,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_palette_24),
                    contentDescription = "palette icon"
                )
            },
            trailingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "check icon"
                    )
                }
            },
            keyboardActions = KeyboardActions(
                onDone = {}

            )
        )
    }
}
