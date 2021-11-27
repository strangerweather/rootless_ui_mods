package com.strangerweather.rootlessuimods.navigation

import android.graphics.drawable.VectorDrawable
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import com.strangerweather.rootlessuimods.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )

    object ColorMods : BottomBarScreen(
        route = "color_mods",
        title = "Color Mods",
        icon =
    )

    object ScreenMods : BottomBarScreen(
        route = "screen_mods",
        title = "Screen Mods",
        icon = Icons.Default.Home
    )
}
