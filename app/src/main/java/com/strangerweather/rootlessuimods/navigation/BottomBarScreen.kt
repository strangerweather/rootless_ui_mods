package com.strangerweather.rootlessuimods.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Palette
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Filled.Home
    )

    object ColorMods : BottomBarScreen(
        route = "color_mods",
        title = "Color Mods",
        icon = Icons.Filled.Palette
    )

    object ScreenMods : BottomBarScreen(
        route = "screen_mods",
        title = "Screen Mods",
        icon = Icons.Filled.Fullscreen
    )
}
