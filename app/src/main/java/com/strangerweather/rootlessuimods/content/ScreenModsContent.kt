package com.strangerweather.rootlessuimods.content

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.strangerweather.rootlessuimods.R
import com.strangerweather.rootlessuimods.components.composables.ActivateButton
import com.strangerweather.rootlessuimods.components.composables.BasicContent
import com.strangerweather.rootlessuimods.components.pickers.resourceValue
import com.strangerweather.rootlessuimods.components.tabs.ScreenModsTabScreen
import com.strangerweather.rootlessuimods.functions.registerLayer

@ExperimentalPagerApi
@ExperimentalGraphicsApi
@Composable
fun ScreenModsContent(
    applicationContext: Context,
    applicationInfo: ApplicationInfo,
) {
    val target = "android"
    val type = 5

    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) Color.DarkGray else Color(0xFFF5F5F5))
    ) {
        item {
            ScreenModsTabScreen()
        }
    }
}
//            Card(
//                Modifier
//                    .fillMaxWidth()
//                    .height(150.dp)
//                    .padding(10.dp), elevation = 7.dp
//            ) {
//
//                            Row {
//                                RegisterButton(
//                                    buttonName = stringResource(id = R.string.nb_frame_height),
//                                    registerLayer = {
//                                        registerLayer(
//                                            context = applicationContext,
//                                            name = "navigation_bar_frame_height",
//                                            target = target,
//                                            overlay = "dimen/navigation_bar_frame_height",
//                                            type = type,
//                                            value = 0
//                                        )
//                                    }
//                                )
//                                Spacer(modifier = Modifier.width(30.dp))
//                                ActivateButton(
//                                    context = applicationContext,
//                                    info = applicationInfo,
//                                    name = "navigation_bar_frame_height",
//                                    target = target
//                                )
//                            }
//                        }
//                    }
//                }
//                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
//                    Column(
//                        Modifier.fillMaxWidth(),
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Row {
//                            RegisterButton(
//                                buttonName = stringResource(id = R.string.nb_height),
//                                registerLayer = {
//                                    registerLayer(
//                                        context = applicationContext,
//                                        name = "navigation_bar_height",
//                                        target = target,
//                                        overlay = "dimen/navigation_bar_height",
//                                        type = type,
//                                        value = 1
//                                    )
//                                }
//                            )
//                        }
//                    }
//                }
//            }


@Composable
fun RegisterButton(
    buttonName: String,
    registerLayer: () -> Unit
) {
    OutlinedButton(
        onClick = {
            registerLayer()
        },
        Modifier
            .height(50.dp)
            .width(135.dp)
    ) {
        Text(text = buttonName, textAlign = TextAlign.Center)
    }
}