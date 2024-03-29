package com.strangerweather.rootlessuimods.content

import android.content.Context
import android.content.pm.ApplicationInfo
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.strangerweather.rootlessuimods.components.composables.BasicContent
import com.strangerweather.rootlessuimods.components.composables.TopCardColorMods
import com.strangerweather.rootlessuimods.components.tabs.ColorModsTabScreen
import com.strangerweather.rootlessuimods.utils.MainViewModel

@ExperimentalGraphicsApi
@ExperimentalPagerApi
@Composable
fun ColorModsContent(applicationContext: Context, applicationInfo: ApplicationInfo) {

    val viewModel: MainViewModel = viewModel()
    val currentPage = viewModel.colorPage.observeAsState()

    LazyColumn(Modifier.fillMaxSize()) {
        val overlay = when (currentPage.value) {
            0 -> "color/system_accent1_100"
            1 -> "color/system_neutral1_50"
            2 -> "color/system_neutral1_900"
            3 -> "color/system_neutral1_0"
            4 -> "color/system_accent1_900"
            else -> ""
        }

        val target = when (currentPage.value) {
            0 -> "android"
            1 -> "android"
            2 -> "android"
            3 -> "android"
            4 -> "android"
            else -> ""
        }

        val name = when (currentPage.value) {
            0 -> "accent1_100"
            1 -> "neutral1_50"
            2 -> "neutral1_900"
            3 -> "neutral1_0"
            4 -> "testing"
            else -> ""
        }

        item {
            ColorModsTabScreen()
            TopCardColorMods(
                context = applicationContext,
                name = name,
                target = target,
                overlay = overlay
            )
            BasicContent(
                context = applicationContext,
                info = applicationInfo,
                name = name,
                target = target
            )
        }
    }
}