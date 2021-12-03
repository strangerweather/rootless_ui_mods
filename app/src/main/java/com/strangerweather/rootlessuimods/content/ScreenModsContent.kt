package com.strangerweather.rootlessuimods.content

import android.content.Context
import android.content.pm.ApplicationInfo
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
//            2 -> "color/system_neutral1_0"
            else -> ""
        }

        val target = "android"
        val type = 5

        val name = when (currentPage.value) {
            0 -> "navigation_bar_frame_height"
            1 -> "navigation_bar_height"
//            2 -> "neutral1_0"
            else -> ""
        }


        val value = when (currentPage.value) {
            0 -> 0
            1 -> 1
//            2 -> "neutral1_0"
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


//    @Composable
//    fun RegisterButton(
//        buttonName: String,
//        registerLayer: () -> Unit
//    ) {
//        OutlinedButton(
//            onClick = {
//                registerLayer()
//            },
//            Modifier
//                .height(50.dp)
//                .width(135.dp)
//        ) {
//            Text(text = buttonName, textAlign = TextAlign.Center)
//        }
//    }
//
//    @Composable
//    fun RemovePillCard(
//        applicationContext: Context,
//        applicationInfo: ApplicationInfo,
//        target: String,
//        type: Int
//    ) {
//        Card(modifier = Modifier.height(400.dp), elevation = 7.dp) {
//            Column(
//                Modifier.padding(20.dp),
//                verticalArrangement = Arrangement.SpaceBetween,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(text = stringResource(id = R.string.remove_pill), fontWeight = FontWeight.Bold)
//                RegisterButton(
//                    buttonName = stringResource(id = R.string.nb_frame_height),
//                    registerLayer = {
//                        registerLayer(
//                            context = applicationContext,
//                            name = "navigation_bar_frame_height",
//                            target = target,
//                            overlay = "dimen/navigation_bar_frame_height",
//                            type = type,
//                            value = 0
//                        )
//                    }
//                )
//                ActivateButton(
//                    context = applicationContext,
//                    info = applicationInfo,
//                    name = "navigation_bar_frame_height",
//                    target = target
//                )
//                RemoveButton(
//                    context = applicationContext,
//                    info = applicationInfo,
//                    name = "navigation_bar_frame_height",
//                    target = target,
//                    buttonName = stringResource(id = R.string.undo)
//                )
//            }
//        }
//    }
//
//    @Composable
//    fun RemoveBarCard(
//        applicationContext: Context,
//        applicationInfo: ApplicationInfo,
//        target: String,
//        type: Int
//    ) {
//        Card(modifier = Modifier.height(400.dp), elevation = 7.dp) {
//            Column(
//                Modifier.padding(20.dp),
//                verticalArrangement = Arrangement.SpaceBetween,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(text = stringResource(id = R.string.remove_nb), fontWeight = FontWeight.Bold)
//                RegisterButton(
//                    buttonName = stringResource(id = R.string.nb_height),
//                    registerLayer = {
//                        registerLayer(
//                            context = applicationContext,
//                            name = "navigation_bar_height",
//                            target = target,
//                            overlay = "dimen/navigation_bar_height",
//                            type = type,
//                            value = 1
//                        )
//                    }
//                )
//                ActivateButton(
//                    context = applicationContext,
//                    info = applicationInfo,
//                    name = "navigation_bar_height",
//                    target = target
//                )
//                RemoveButton(
//                    context = applicationContext,
//                    info = applicationInfo,
//                    name = "navigation_bar_height",
//                    target = target,
//                    buttonName = stringResource(id = R.string.undo)
//                )
//            }
//        }
//    }
