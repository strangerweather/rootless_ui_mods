package com.strangerweather.rootlessuimods.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.strangerweather.rootlessuimods.R

@Composable
fun ColorTextField() {

    var text by remember { mutableStateOf("") }
    val maxChar = 6

    Column(
        Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        OutlinedTextField(value = text, onValueChange = { colorCode ->
            if (colorCode.length <= maxChar){
                text = colorCode
                println(text)
            }
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

            keyboardOptions = KeyboardOptions(
                KeyboardCapitalization.Characters
            ),
            keyboardActions = KeyboardActions(
                onDone = {}

            )
        )
    }
}
