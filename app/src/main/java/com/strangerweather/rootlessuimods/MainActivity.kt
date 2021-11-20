package com.strangerweather.rootlessuimods

import android.app.AlertDialog
import android.content.Context
import android.content.pm.ApplicationInfo
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.strangerweather.rootlessuimods.ui.theme.RootlessUIModsTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.lsposed.hiddenapibypass.HiddenApiBypass
import tk.zwander.fabricateoverlay.FabricatedOverlay
import tk.zwander.fabricateoverlay.FabricatedOverlayEntry
import tk.zwander.fabricateoverlay.OverlayAPI
import tk.zwander.fabricateoverlay.ShizukuUtils


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HiddenApiBypass.setHiddenApiExemptions("L")

        if (ShizukuUtils.shizukuAvailable) {
            if (ShizukuUtils.hasShizukuPermission(this)) {
                setAppContent()
            } else {
                ShizukuUtils.requestShizukuPermission(this) { granted ->
                    if (granted) {
                        setAppContent()
                    } else {
                        runBlocking {
                            launch {
                                delay(3000L)
                                finish()
                            }
                            Toast.makeText(
                                applicationContext,
                                "Too bad, exiting!",
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    }
                }
            }
        } else {
            showShizukuDialog(this)
        }
    }

    private fun showShizukuDialog(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(R.string.shizuku_needed_title)
            .setMessage(R.string.shizuku_needed_text)
            .setCancelable(false)
            .create()
            .show()
    }


    private fun setAppContent() {
        setContent {
            RootlessUIModsTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val context = LocalContext.current
                    HomePageButtons(
                        context = context,
                        info = applicationInfo,
                        name = "accent1100_6",
                        target = "android"
                    )
                }
            }
        }
    }


    private fun registerLayer(context: Context, name: String, target: String) {
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

    private fun enableLayer(context: Context, info: ApplicationInfo, name: String, target: String) {
        OverlayAPI.getInstance(context) { api ->
            api.setEnabled(
                FabricatedOverlay.generateOverlayIdentifier(
                    "com.strangerweather.rootlessuimods.$target.$name",
                    "com.android.shell"
                ), info.enabled, 0
            )
        }
    }

    private fun disableLayer(
        context: Context,
        info: ApplicationInfo,
        name: String,
        target: String
    ) {
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
            Button(onClick = {
                registerLayer(context, name, target)
            }) {
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
}


//    .\adb shell sh /storage/emulated/0/Android/data/moe.shizuku.privileged.api/start.sh
//    .\adb shell cmd overlay disable --user current com.android.shell:com.strangerweather.rootlessuimods.android.test
//    .\adb shell cmd overlay list
