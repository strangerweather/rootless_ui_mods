package com.strangerweather.rootlessuimods.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.strangerweather.rootlessuimods.R
import com.strangerweather.rootlessuimods.utils.MainViewModel


object HexToJetpackColor {
    fun getColor(colorString: String): Color {
        return Color(android.graphics.Color.parseColor("#$colorString"))
    }
}

@Composable
fun ColorTextField() {

    val viewModel: MainViewModel = viewModel()
    var hex by remember { mutableStateOf("") }
    val maxChar = 6

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
            label = { Text(text = "Enter Hex Value") }, singleLine = true,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_palette_24),
                    contentDescription = "palette icon"
                )
            },
            trailingIcon = {
                IconButton(onClick = {
                    viewModel.onConvertedHexChanged(HexToJetpackColor.getColor(hex).toArgb())
                }) {
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
                onDone = {
                    viewModel.onConvertedHexChanged(
                        HexToJetpackColor.getColor(hex).toArgb()
                    )
                }

            )
        )
    }
}
