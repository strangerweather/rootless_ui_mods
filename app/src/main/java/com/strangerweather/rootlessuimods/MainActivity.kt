package com.strangerweather.rootlessuimods

import android.content.Context
import android.content.pm.ApplicationInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.strangerweather.rootlessuimods.ui.theme.RootlessUIModsTheme
import org.lsposed.hiddenapibypass.HiddenApiBypass
import tk.zwander.fabricateoverlay.FabricatedOverlay
import tk.zwander.fabricateoverlay.FabricatedOverlayEntry
import tk.zwander.fabricateoverlay.OverlayAPI
import tk.zwander.fabricateoverlay.ShizukuUtils


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HiddenApiBypass.setHiddenApiExemptions("L")

        setContent {
            RootlessUIModsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val overlayEntries = remember { mutableStateListOf<FabricatedOverlayEntry>() }
                    val context = LocalContext.current

                    if (!ShizukuUtils.shizukuAvailable) {
                        ShowShizukuDialog()
                    }

                    //TODO: add permission dialog
                    if (ShizukuUtils.hasShizukuPermission(this)) {
//                        SaveOverlay(
//                            context = context,
//                            info = applicationInfo,
//                            overlayEntries = overlayEntries
//                        )
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
}

@Composable
fun SaveOverlay(
    context: Context,
    info: ApplicationInfo,
    overlayEntries: List<FabricatedOverlayEntry>
) {
    val name by remember { mutableStateOf("") }

    TextButton(
        onClick = {
            OverlayAPI.getInstance(context) { api ->
                api.registerFabricatedOverlay(
                    FabricatedOverlay(
                        "${context.packageName}.${info.packageName}.${name}",
                        info.packageName
                    ).apply {
                        overlayEntries.forEach { overlay ->
                            entries[overlay.resourceName] = overlay
                        }
                    }
                )
            }
        }) { Text("Save") }
}

//adb shell sh /storage/emulated/0/Android/data/moe.shizuku.privileged.api/start.sh