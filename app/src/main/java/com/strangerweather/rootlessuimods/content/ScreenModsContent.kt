package com.strangerweather.rootlessuimods.content

import android.content.Context
import android.content.pm.ApplicationInfo
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.strangerweather.rootlessuimods.components.BasicContent
import com.strangerweather.rootlessuimods.functions.enableLayer
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

    Column() {
        Button(onClick = {
            registerLayer(applicationContext, name, target, overlay, type, value)
        }) {

        }
        BasicContent(
            context = applicationContext,
            info = applicationInfo,
            name = name,
            target = target
        )
    }
}