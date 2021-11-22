package com.strangerweather.rootlessuimods.components

import android.content.Context
import android.content.pm.ApplicationInfo
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.strangerweather.rootlessuimods.R
import com.strangerweather.rootlessuimods.components.dialogs.ColorPickerDialog
import com.strangerweather.rootlessuimods.functions.disableLayer
import com.strangerweather.rootlessuimods.functions.enableLayer
import com.strangerweather.rootlessuimods.ui.theme.LightGreen200
import com.strangerweather.rootlessuimods.ui.theme.Purple200
import com.strangerweather.rootlessuimods.ui.theme.Purple500

@ExperimentalPagerApi
@ExperimentalGraphicsApi
@Composable
fun BasicContent(context: Context, info: ApplicationInfo, name: String, target: String, overlay: String) {
    val showAlertDialog = remember { mutableStateOf(false) }
    var progress by remember { mutableStateOf(0.1f) }
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )

    if (showAlertDialog.value) {
        AlertDialogView(state = showAlertDialog, context = context, name = name, target = target, overlay = overlay)
    }


    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) Color.DarkGray else Color(0xFFF5F5F5))
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
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        OutlinedButton(
                            onClick = { showAlertDialog.value = true },
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
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        Column(
                            Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            OutlinedButton(
                                onClick = {
                                    if (progress < 1f) progress += 0.1f
                                    enableLayer(context, info, name, target)
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
            Card(
                Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(10.dp), elevation = 7.dp
            ) {
                Column(
                    Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    OutlinedButton(
                        onClick = { disableLayer(context, info, name, target) },
                        Modifier
                            .height(50.dp)
                            .width(135.dp)
                    ) {
                        Text(text = stringResource(id = R.string.remove))
                    }
                }
            }
        }
    }
}

@ExperimentalGraphicsApi
@Composable
fun AlertDialogView(state: MutableState<Boolean>, context: Context, name: String, target: String, overlay:String) {
    ColorPickerDialog(state, context, name, target, overlay)
}
