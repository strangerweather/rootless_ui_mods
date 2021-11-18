package com.strangerweather.rootlessuimods

import android.content.Context
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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
                        systemMods(context = context)
//                        systemUiMods(context = context)

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
//                            )
//                        )

//                        val overlayEntries = listOf(
//                            FabricatedOverlayEntry(
//                                resourceName = "android:dimen/navigation_bar_height",
//                                resourceType = TypedValue.COMPLEX_UNIT_DIP,
//                                resourceValue = 0
//                            )
//                        )
//
//
//                        // For com.android.systemui
//                        OverlayAPI.getInstance(context) { api ->
//                            api.registerFabricatedOverlay(
//                                FabricatedOverlay(
//                                    "com.strangerweather.rootlessuimods.com.android.systemui.test",
//                                    "com.android.systemui"
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
//                                    "com.strangerweather.rootlessuimods.com.android.systemui.test",
//                                    "com.android.shell"
//                                ), info.enabled, 0
//                            )
//                        }
//                    }
//                }

                        // For android (system)


                    }
                }
            }
        }
    }

//    private fun systemUiMods(context: Context) = runBlocking { // this: CoroutineScope
//        launch { // launch a new coroutine and continue
//            delay(3000L)
//
//            val info = applicationInfo
//
//            val overlayEntries = listOf(
//                FabricatedOverlayEntry(
//                    resourceName = "com.android.systemui:color/accent_material_light",
//                    resourceType = TypedValue.TYPE_INT_COLOR_ARGB8,
//                    resourceValue = 11766015
//                )
//            )
//            OverlayAPI.getInstance(context) { api ->
//                api.registerFabricatedOverlay(
//                    FabricatedOverlay(
//                        "com.strangerweather.rootlessuimods.com.android.systemui.ml",
//                        "com.android.systemui"
//                    ).apply {
//                        overlayEntries.forEach { overlay ->
//                            entries[overlay.resourceName] = overlay
//                        }
//                    }
//                )
//            }
//
//            OverlayAPI.getInstance(context) { api ->
//                api.setEnabled(
//                    FabricatedOverlay.generateOverlayIdentifier(
//                        "com.strangerweather.rootlessuimods.com.android.systemui.ml",
//                        "com.android.shell"
//                    ), info.enabled, 0
//                )
//            }
//        }
//    }


    private fun systemMods(context: Context) = runBlocking { // this: CoroutineScope
        launch { // launch a new coroutine and continue
            delay(3000L)

            val info = applicationInfo

            val overlayEntries = listOf(
                FabricatedOverlayEntry(
                    resourceName = "android:color/system_neutral1_50",
                    resourceType = 28,
                    resourceValue = 11766015
                )
            )// non-blocking delay for 1 second (default time unit is ms)
            OverlayAPI.getInstance(context) { api ->
                api.registerFabricatedOverlay(
                    FabricatedOverlay(
                        "com.strangerweather.rootlessuimods.android.test4",
                        "android"
                    ).apply {
                        overlayEntries.forEach { overlay ->
                            entries[overlay.resourceName] = overlay
                        }
                    }
                )
            }

            OverlayAPI.getInstance(context) { api ->
                api.setEnabled(
                    FabricatedOverlay.generateOverlayIdentifier(
                        "com.strangerweather.rootlessuimods.android.test4",
                        "com.android.shell"
                    ), info.enabled, 0
                )
            }

            println("World!") // print after delay
        }
        println("Hello")
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

// CODING HELPERS

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


//    .\adb shell sh /storage/emulated/0/Android/data/moe.shizuku.privileged.api/start.sh
//    .\adb shell cmd overlay disable --user current com.android.shell:com.strangerweather.rootlessuimods.android.test
//    .\adb shell cmd overlay list

//Overlay manager (overlay) commands:
//help
//Print this help text.
//dump [--verbose] [--user USER_ID] [[FIELD] PACKAGE[:NAME]]
//Print debugging information about the overlay manager.
//With optional parameters PACKAGE and NAME, limit output to the specified
//overlay or target. With optional parameter FIELD, limit output to
//the corresponding SettingsItem field. Field names are all lower case
//and omit the m prefix, i.e. 'userid' for SettingsItem.mUserId.
//list [--user USER_ID] [PACKAGE[:NAME]]
//Print information about target and overlay packages.
//Overlay packages are printed in priority order. With optional
//parameters PACKAGE and NAME, limit output to the specified overlay or
//target.
//enable [--user USER_ID] PACKAGE[:NAME]
//Enable overlay within or owned by PACKAGE with optional unique NAME.
//disable [--user USER_ID] PACKAGE[:NAME]
//Disable overlay within or owned by PACKAGE with optional unique NAME.
//enable-exclusive [--user USER_ID] [--category] PACKAGE
//Enable overlay within or owned by PACKAGE and disable all other overlays
//for its target package. If the --category option is given, only disables
//other overlays in the same category.
//set-priority [--user USER_ID] PACKAGE PARENT|lowest|highest
//Change the priority of the overlay to be just higher than
//the priority of PARENT If PARENT is the special keyword
//'lowest', change priority of PACKAGE to the lowest priority.
//If PARENT is the special keyword 'highest', change priority of
//PACKAGE to the highest priority.
//lookup [--user USER_ID] [--verbose] PACKAGE-TO-LOAD PACKAGE:TYPE/NAME
//Load a package and print the value of a given resource
//applying the current configuration and enabled overlays.
//For a more fine-grained alternative, use 'idmap2 lookup'.
//fabricate [--user USER_ID] [--target-name OVERLAYABLE] --target PACKAGE
//--name NAME PACKAGE:TYPE/NAME ENCODED-TYPE-ID ENCODED-VALUE
//Create an overlay from a single resource. Caller must be root. Example:
//fabricate --target android --name LighterGray \
//android:color/lighter_gray 0x1c 0xffeeeeee