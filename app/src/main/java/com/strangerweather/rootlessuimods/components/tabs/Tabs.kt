package com.strangerweather.rootlessuimods.components.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.strangerweather.rootlessuimods.R
import com.strangerweather.rootlessuimods.ui.theme.Purple500


@Composable
fun Tabs(tabTitle: String, tabText: String) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    )
    {

            Card(
                Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(10.dp), elevation = 7.dp
            ) {
                Column(
                    Modifier
                        .padding(15.dp)
                        .fillMaxSize()
                ) {
                    Text(
                        text = tabTitle, style = TextStyle(
                            Purple500, fontSize = 20.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = tabText,
                    )
                }
            }
        }
    }

