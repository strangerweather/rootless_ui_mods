package com.strangerweather.rootlessuimods

import android.content.Context
import android.content.pm.ApplicationInfo
import android.graphics.ColorSpace
import android.os.Bundle
import android.util.TypedValue
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
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
                        HomePageButtons(
                            context = context,
                            info = applicationInfo,
                            name = "accent1100_6",
                            target = "android"
                            // if system type "android", else if systemui type "com.android.systemui"
                        )
                    }
                }
            }
        }
    }
}

fun registerLayer(context: Context, name: String, target: String) {
    val overlayEntries = listOf(
        FabricatedOverlayEntry(
            resourceName = "$target:color/system_accent1_100",
            resourceType = 28,
            resourceValue = -2001105
        )
    )
    OverlayAPI.getInstance(context) { api ->
        api.registerFabricatedOverlay(
            FabricatedOverlay(
                "com.strangerweather.rootlessuimods.$target.$name",
                target
            ).apply {
                overlayEntries.forEach { overlay ->
                    entries[overlay.resourceName] = overlay
                }
            }
        )
    }
}

fun enableLayer(context: Context, info: ApplicationInfo, name: String, target: String) {
    OverlayAPI.getInstance(context) { api ->
        api.setEnabled(
            FabricatedOverlay.generateOverlayIdentifier(
                "com.strangerweather.rootlessuimods.$target.$name",
                "com.android.shell"
            ), info.enabled, 0
        )
    }
}

fun disableLayer(context: Context, info: ApplicationInfo, name: String, target: String) {
    OverlayAPI.getInstance(context) { api ->
        api.setEnabled(
            FabricatedOverlay.generateOverlayIdentifier(
                "com.strangerweather.rootlessuimods.$target.$name",
                "com.android.shell"
            ), !info.enabled, 0
        )
    }
}


@Composable
fun HomePageButtons(context: Context, info: ApplicationInfo, name: String, target: String) {
    Row(
        Modifier
            .padding(top = 100.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(onClick = { registerLayer(context, name, target) }) {
            Text(text = "Register")
        }
        Spacer(modifier = Modifier.width(30.dp))
        Button(onClick = { enableLayer(context, info, name, target) }) {
            Text(text = "Enable")
        }
        Spacer(modifier = Modifier.width(30.dp))
        Button(onClick = { disableLayer(context, info, name, target) }) {
            Text(text = "Disable")
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

//    .\adb shell sh /storage/emulated/0/Android/data/moe.shizuku.privileged.api/start.sh
//    .\adb shell cmd overlay disable --user current com.android.shell:com.strangerweather.rootlessuimods.android.test
//    .\adb shell cmd overlay list
