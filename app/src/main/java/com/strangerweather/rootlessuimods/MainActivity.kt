package com.strangerweather.rootlessuimods

import android.content.pm.ApplicationInfo
import android.os.Bundle
import android.util.TypedValue
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.strangerweather.rootlessuimods.ui.theme.RootlessUIModsTheme
import org.lsposed.hiddenapibypass.HiddenApiBypass
import tk.zwander.fabricateoverlay.*
import java.util.function.Consumer


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

                        val overlayEntries = listOf(
                            FabricatedOverlayEntry(
                                resourceName = "com.android.systemui:integer/quick_settings_num_columns",
                                resourceType = 16,
                                resourceValue = 3
                            )
                        )


                        OverlayAPI.getInstance(context) { api ->
                            api.registerFabricatedOverlay(
                                FabricatedOverlay(
                                    "com.strangerweather.rootlessuimods.com.android.systemui.test",
                                    "com.android.systemui"
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
                                    "com.strangerweather.rootlessuimods.com.android.systemui.test",
                                    "com.android.shell"
                                ), info.enabled, 0
                            )
                        }


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


//.\adb shell sh /storage/emulated/0/Android/data/moe.shizuku.privileged.api/start.sh