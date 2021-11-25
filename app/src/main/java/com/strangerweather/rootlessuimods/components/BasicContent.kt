package com.strangerweather.rootlessuimods.components

import android.content.Context
import android.content.pm.ApplicationInfo
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.strangerweather.rootlessuimods.R
import com.strangerweather.rootlessuimods.components.dialogs.ColorPickerDialog
import com.strangerweather.rootlessuimods.functions.deleteLayer
import com.strangerweather.rootlessuimods.functions.disableLayer
import com.strangerweather.rootlessuimods.functions.enableLayer
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@ExperimentalPagerApi
@ExperimentalGraphicsApi
@Composable
fun BasicContent(
    context: Context,
    info: ApplicationInfo,
    name: String,
    target: String,
    overlay: String
) {
    val showAlertDialog = remember { mutableStateOf(false) }

    if (showAlertDialog.value) {
        AlertDialogView(
            state = showAlertDialog,
            context = context,
            name = name,
            target = target,
            overlay = overlay
        )
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
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedButton(
                            onClick = { showAlertDialog.value = true },
                            Modifier
                                .height(50.dp)
                                .width(135.dp),
                        ) {
                            Text(text = stringResource(id = R.string.color_picker))
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
                                    enableLayer(context, info, name, target)
                                },
                                Modifier
                                    .height(50.dp)
                                    .width(135.dp)
                            ) {
                                Text(text = stringResource(id = R.string.activate))
                            }
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
                        onClick = {
                            runBlocking {
                                removeAndDelete(context, info, name, target)
                            }
                        },
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

suspend fun removeAndDelete(
    context: Context,
    info: ApplicationInfo,
    name: String,
    target: String
) = coroutineScope { // this: CoroutineScope
    disableLayer(context, info, name, target)
    deleteLayer(context, name, target)
}

@ExperimentalGraphicsApi
@Composable
fun AlertDialogView(
    state: MutableState<Boolean>,
    context: Context,
    name: String,
    target: String,
    overlay: String
) {
    ColorPickerDialog(state, context, name, target, overlay)
}
