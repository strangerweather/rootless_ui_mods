package com.strangerweather.rootlessuimods.components.pickers

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Palette
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.strangerweather.rootlessuimods.functions.registerLayer
import com.strangerweather.rootlessuimods.ui.theme.Purple500


object HexToJetpackColor {
    fun getColor(colorString: String): Color {
        return Color(android.graphics.Color.parseColor("#$colorString"))
    }
}

@Composable
fun ColorTextField(context: Context, name: String, target: String, overlay: String) {
    var hex by remember { mutableStateOf("") }
    val maxChar = 6

    fun action() {
        if (hex.isNotEmpty()) {
            registerLayer(
                context,
                name,
                target,
                overlay,
                28,
                HexToJetpackColor.getColor(hex).toArgb()
            )
        } else {
            Toast.makeText(context, "Value Needed", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        OutlinedTextField(value = hex, onValueChange = { colorCode ->
            if (colorCode.length <= maxChar) {
                hex = colorCode
            }
        },
            label = { Text(text = "Enter Hex Value") },
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Purple500,
                unfocusedBorderColor = Purple500
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Palette,
                    contentDescription = "palette icon",
                    tint = Purple500
                )
            },
            trailingIcon = {
                IconButton(onClick = {
                    action()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "check icon",
                        tint = Purple500
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                KeyboardCapitalization.Characters
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    action()
                }
            )
        )
    }
}


