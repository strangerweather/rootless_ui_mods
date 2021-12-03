package com.strangerweather.rootlessuimods.components.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopCardScreenMods(registerLayer: () -> Unit, buttonName: String) {
    Column(
        Modifier.background(if (isSystemInDarkTheme()) Color.DarkGray else Color(0xFFF5F5F5))
    ) {
        Card(
            Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(10.dp), elevation = 2.dp
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
                        onClick = { registerLayer() },
                        Modifier
                            .height(50.dp)
                            .width(135.dp),
                    ) {
                        Text(text = buttonName)
                    }
                }
            }
        }
    }
}
