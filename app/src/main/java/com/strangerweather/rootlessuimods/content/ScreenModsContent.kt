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
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.strangerweather.rootlessuimods.R
import com.strangerweather.rootlessuimods.components.composables.BasicContent
import com.strangerweather.rootlessuimods.functions.registerLayer

@ExperimentalPagerApi
@ExperimentalGraphicsApi
@Composable
fun ScreenModsContent(
    applicationContext: Context,
    applicationInfo: ApplicationInfo,
) {

    val name = "navigation_bar_frame_height"
    val target = "android"
    val overlay = "dimen/navigation_bar_frame_height"
    val type = 5
    val value = 1

//    Column() {
//        Button(onClick = {
//            registerLayer(applicationContext, name, target, overlay, type, value)
//        }) {
//
//        }

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
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        Column(
                            Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            OutlinedButton(
                                onClick = {
                                    registerLayer(applicationContext, name, target, overlay, type, value)
                                },
                                Modifier
                                    .height(50.dp)
                                    .width(135.dp)
                            ) {
                                Text(text = stringResource(id = R.string.apply))
                            }
                        }
                    }
                }
            }
        }
    }
    BasicContent(
        context = applicationContext,
        info = applicationInfo,
        name = name,
        target = target
    )
}
