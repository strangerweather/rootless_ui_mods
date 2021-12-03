package com.strangerweather.rootlessuimods.content

import android.content.Context
import android.content.pm.ApplicationInfo
import android.util.TypedValue
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.strangerweather.rootlessuimods.components.composables.BasicContent
import com.strangerweather.rootlessuimods.components.composables.TopCardScreenMods
import com.strangerweather.rootlessuimods.components.tabs.ScreenModsTabScreen
import com.strangerweather.rootlessuimods.functions.registerLayer
import com.strangerweather.rootlessuimods.utils.MainViewModel
import com.strangerweather.rootlessuimods.R
import tk.zwander.fabricateoverlay.FabricatedOverlay

@ExperimentalPagerApi
@ExperimentalGraphicsApi
@Composable
fun ScreenModsContent(
    applicationContext: Context,
    applicationInfo: ApplicationInfo,
) {
    val viewModel: MainViewModel = viewModel()
    val currentPage = viewModel.screenPage.observeAsState()

    LazyColumn(Modifier.fillMaxSize()) {

        val overlay = when (currentPage.value) {
            0 -> "dimen/navigation_bar_frame_height"
            1 -> "dimen/navigation_bar_height"
            2 -> "dimen/status_bar_battery_icon_width"
            3 -> "dimen/status_bar_icon_size"
            4 -> "dimen/status_bar_clock_size"
            else -> ""
        }

        val target = when (currentPage.value) {
            0 -> "android"
            1 -> "android"
            2 -> "com.android.systemui"
            3 -> "android"
            4 -> "com.android.systemui"
            else -> ""
        }
        val type = when (currentPage.value) {
            0 -> 5
            1 -> 5
            2 -> 5
            3 -> 5
            4 -> 5
            else -> -1
        }

        val name = when (currentPage.value) {
            0 -> "navigation_bar_frame_height"
            1 -> "navigation_bar_height"
            2 -> "status_bar_battery_icon_width"
            3 -> "status_bar_icon_size"
            4 -> "status_bar_clock_size"
            else -> ""
        }


        val value = when (currentPage.value) {
            0 -> 0
            1 -> 1
            2 -> 0
            3 -> 0
            4 -> 0
            else -> -1
        }

        item {
            ScreenModsTabScreen()
            TopCardScreenMods(
                {
                    registerLayer(
                        context = applicationContext,
                        name = name,
                        target = target,
                        overlay = overlay,
                        type = type,
                        value = value
                    )

                },
                stringResource(id = R.string.register)
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
