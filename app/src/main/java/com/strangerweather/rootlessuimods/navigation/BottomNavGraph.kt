package com.strangerweather.rootlessuimods.navigation

import android.content.Context
import android.content.pm.ApplicationInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.strangerweather.rootlessuimods.content.ColorModsContent
import com.strangerweather.rootlessuimods.content.HomeScreen
import com.strangerweather.rootlessuimods.content.ScreenModsContent

@ExperimentalPagerApi
@ExperimentalGraphicsApi
@Composable
fun BottomNavGraph(navController: NavHostController, context: Context, info: ApplicationInfo) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) { HomeScreen() }
        composable(route = BottomBarScreen.ColorMods.route) {
            ColorModsContent(
                applicationContext = context,
                applicationInfo = info
            )
        }
        composable(route = BottomBarScreen.ScreenMods.route) {
            ScreenModsContent()
        }
    }
}