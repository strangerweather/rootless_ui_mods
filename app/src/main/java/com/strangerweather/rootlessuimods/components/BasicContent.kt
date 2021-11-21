package com.strangerweather.rootlessuimods.components

import android.content.Context
import android.content.pm.ApplicationInfo
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.strangerweather.rootlessuimods.R
import com.strangerweather.rootlessuimods.functions.enableLayer
import com.strangerweather.rootlessuimods.ui.theme.Purple500

@Composable
fun BasicContent() {
    val progress by remember { mutableStateOf(0.1f) }
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )
    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        item {
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
                        text = stringResource(id = R.string.tab1_instr_1), style = TextStyle(
                            Purple500, fontSize = 20.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        OutlinedButton(
                            onClick = { },
                            Modifier
                                .height(50.dp)
                                .width(135.dp),
                        ) {
                            Text(text = stringResource(id = R.string.color_picker))
                        }
                        OutlinedButton(
                            onClick = { /*TODO*/ },
                            Modifier
                                .height(50.dp)
                                .width(135.dp)
                        ) {
                            Text(text = stringResource(id = R.string.color_presets))
                        }
                    }
                }
            }
            Card(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(10.dp), elevation = 7.dp
            ) {
                Column(
                    Modifier
                        .padding(15.dp)
                        .fillMaxSize()
                ) {
                    Text(
                        text = stringResource(id = R.string.tab1_instr_2), style = TextStyle(
                            Purple500, fontSize = 20.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center ) {
                        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                            OutlinedButton(
                                onClick = {
//                                enableLayer(context, info, name, target)
                                },
                                Modifier
                                    .height(50.dp)
                                    .width(135.dp)
                            ) {
                                Text(text = stringResource(id = R.string.activate))
                            }

                            Spacer(modifier = Modifier.height(40.dp))
                            LinearProgressIndicator(progress = animatedProgress)
                        }
                    }
                }
            }
        }
    }
}
