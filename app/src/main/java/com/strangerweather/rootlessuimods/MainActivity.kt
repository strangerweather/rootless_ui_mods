package com.strangerweather.rootlessuimods

import android.graphics.ColorSpace
import android.os.Bundle
import android.util.TypedValue
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.strangerweather.rootlessuimods.ui.theme.RootlessUIModsTheme
import org.lsposed.hiddenapibypass.HiddenApiBypass
import tk.zwander.fabricateoverlay.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HiddenApiBypass.setHiddenApiExemptions("L")

        setContent {
            RootlessUIModsTheme {
                Surface(color = MaterialTheme.colors.background) {


                    if (!ShizukuUtils.shizukuAvailable) {
                        ShowShizukuDialog()
                    }

                    //TODO: add permission dialog
                    if (ShizukuUtils.hasShizukuPermission(this)) {

                        val context = LocalContext.current
                        val info = applicationInfo


//                        val overlayEntries = listOf(
//                            FabricatedOverlayEntry(
//                                resourceName = "android:color/system_neutral1_50",
//                                resourceType = 28,
//                                resourceValue = -5625816
//                            )
//                        )

//                        val overlayEntries = listOf(
//                            FabricatedOverlayEntry(
//                                resourceName = "com.android.systemui:integer/quick_settings_num_columns",
//                                resourceType = TypedValue.TYPE_INT_DEC,
//                                resourceValue = 3
//                            )
//                        )
//
//                        val overlayEntries = listOf(
//                            FabricatedOverlayEntry(
//                                resourceName = "android:dimen/navigation_bar_frame_height",
//                                resourceType = TypedValue.COMPLEX_UNIT_DIP,
//                                resourceValue = 0
//                            ),
//                            FabricatedOverlayEntry(
//                                resourceName = "android:dimen/navigation_bar_height",
//                                resourceType = TypedValue.COMPLEX_UNIT_DIP,
//                                resourceValue = 0
//                            )
//                        )
//
//
//                        OverlayAPI.getInstance(context) { api ->
//                            api.registerFabricatedOverlay(
//                                FabricatedOverlay(
//                                    "com.strangerweather.rootlessuimods.android.test",
//                                    "android"
//                                ).apply {
//                                    overlayEntries.forEach { overlay ->
//                                        entries[overlay.resourceName] = overlay
//                                    }
//                                }
//                            )
//                        }
//
//                        OverlayAPI.getInstance(context) { api ->
//                            api.setEnabled(
//                                FabricatedOverlay.generateOverlayIdentifier(
//                                    "com.strangerweather.rootlessuimods.android.test",
//                                    "com.android.shell"
//                                ), info.enabled, 0
//                            )
//                        }


//                        var showingSaveDialog by remember { mutableStateOf(true) }

//                        Box() {
//                            if (showingSaveDialog) {
//                                SaveOverlayDialog(
//                                    info = applicationInfo,
//                                    onDismiss = { showingSaveDialog = false },
//                                    overlayEntries = listOf(
//                                        FabricatedOverlayEntry(
//                                            resourceName = "com.android.systemui:integer/quick_settings_num_columns",
//                                            resourceType = TypedValue.TYPE_DIMENSION,
//                                            resourceValue = 3
//                                        )
//                                    )
//                                )
//                            }
//                            RegisteredOverlayItem(
//                                info = OverlayInfo(
//                                    packageName = "com.android.shell",
//                                    isFabricated = true,
//                                    baseCodePath = "/data/resource-cache/com.android.shell-com.strangerweather.rootlessuimods.com.android.systemui.test-oBfs.frro",
//                                    category = null,
//                                    isMutable = true,
//                                    overlayName = "com.strangerweather.rootlessuimods.com.android.systemui.test",
//                                    priority = 2147483647,
//                                    state = 2,
//                                    targetOverlayableName = "",
//                                    targetPackageName = "com.android.systemui",
//                                    userId = 0
//                                ), onChange = {println("Changed!")}
//                            )
//                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ShowShizukuDialog() {
    AlertDialog(modifier = Modifier.width(200.dp),
        title = { Text(stringResource(id = R.string.shizuku_needed_title)) },
        text = { Text(stringResource(id = R.string.shizuku_needed_text)) },
        buttons = {},
        onDismissRequest = {})
}


//        packageName = "com.android.shell",
//        isFabricated = true,
//        baseCodePath = "/data/resource-cache/com.android.shell-com.strangerweather.rootlessuimods.com.android.systemui.test-oBfs.frro",
//        category = null,
//        isMutable = true,
//        overlayName = "com.strangerweather.rootlessuimods.com.android.systemui.test",
//        priority = 2147483647,
//        state = 2,
//        targetOverlayableName = "",
//        targetPackageName = "com.android.systemui",
//        userId = 0


//        println("packageName =${info.packageName}")
//        println("isFabricated = ${info.isFabricated}")
//        println("BCP = ${info.baseCodePath}")
//        println("category = ${info.category}")
//        println("isMutable = ${info.isMutable}")
//        println("overlayName = ${info.overlayName}")
//        println("priority = ${info.priority}")
//        println("state =${info.state}")
//        println("TON = ${info.targetOverlayableName}")
//        println("TPN = ${info.targetPackageName}")
//        println("UserId =${info.userId}")

//TypedValue.TYPE_INT_DEC -> info.resourceValue.toString()
//TypedValue.TYPE_INT_COLOR_ARGB8 -> "0x${"%1$08x".format(info.resourceValue)}"
//TypedValue.TYPE_INT_BOOLEAN -> (info.resourceValue == 1).toString()
//TypedValue.TYPE_DIMENSION -> TypedValue.coerceToString(info.resourceType, info.resourceValue)


//.\adb shell sh /storage/emulated/0/Android/data/moe.shizuku.privileged.api/start.sh