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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.strangerweather.rootlessuimods.R
import com.strangerweather.rootlessuimods.components.composables.ActivateButton
import com.strangerweather.rootlessuimods.components.composables.RemoveButton
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
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
            ) {
                RemovePillCard(
                    applicationContext = applicationContext,
                    applicationInfo = applicationInfo,
                    target = target,
                    type = type
                )
                Spacer(modifier = Modifier.width(10.dp))
                RemoveBarCard(
                    applicationContext = applicationContext,
                    applicationInfo = applicationInfo,
                    target = target,
                    type = type
                )
            }
        }
    }
}


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

@Composable
fun RemovePillCard(
    applicationContext: Context,
    applicationInfo: ApplicationInfo,
    target: String,
    type: Int
) {
    Card(elevation = 7.dp) {
        Column(
            Modifier.padding(15.dp)
        ) {
            RegisterButton(
                buttonName = stringResource(id = R.string.nb_frame_height),
                registerLayer = {
                    registerLayer(
                        context = applicationContext,
                        name = "navigation_bar_frame_height",
                        target = target,
                        overlay = "dimen/navigation_bar_frame_height",
                        type = type,
                        value = 0
                    )
                }
            )
            Spacer(modifier = Modifier.height(30.dp))
            ActivateButton(
                context = applicationContext,
                info = applicationInfo,
                name = "navigation_bar_frame_height",
                target = target
            )
            Spacer(modifier = Modifier.height(30.dp))
            RemoveButton(
                context = applicationContext,
                info = applicationInfo,
                name = "navigation_bar_frame_height",
                target = target,
                buttonName = stringResource(id = R.string.undo)
            )
        }
    }
}

@Composable
fun RemoveBarCard(
    applicationContext: Context,
    applicationInfo: ApplicationInfo,
    target: String,
    type: Int
) {
    Card(elevation = 7.dp) {
        Column(
            Modifier.padding(15.dp)
        ) {
            RegisterButton(
                buttonName = stringResource(id = R.string.nb_height),
                registerLayer = {
                    registerLayer(
                        context = applicationContext,
                        name = "navigation_bar_height",
                        target = target,
                        overlay = "dimen/navigation_bar_height",
                        type = type,
                        value = 1
                    )
                }
            )
            Spacer(modifier = Modifier.height(30.dp))
            ActivateButton(
                context = applicationContext,
                info = applicationInfo,
                name = "navigation_bar_height",
                target = target
            )
            Spacer(modifier = Modifier.height(30.dp))
            RemoveButton(
                context = applicationContext,
                info = applicationInfo,
                name = "navigation_bar_height",
                target = target,
                buttonName = stringResource(id = R.string.undo)
            )
        }
    }
}
