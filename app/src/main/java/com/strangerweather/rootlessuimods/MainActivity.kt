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
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.strangerweather.rootlessuimods.ui.theme.RootlessUIModsTheme
import com.strangerweather.rootlessuimods.utils.ColorPicker
import com.strangerweather.rootlessuimods.utils.registerLayer
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.lsposed.hiddenapibypass.HiddenApiBypass
import tk.zwander.fabricateoverlay.FabricatedOverlay
import tk.zwander.fabricateoverlay.FabricatedOverlayEntry
import tk.zwander.fabricateoverlay.OverlayAPI
import tk.zwander.fabricateoverlay.ShizukuUtils


@ExperimentalGraphicsApi
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


    @ExperimentalGraphicsApi
    private fun setAppContent() {
        setContent {
            RootlessUIModsTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val context = LocalContext.current
                    Column {
                        ColorPicker()
                        Spacer(modifier = Modifier.height(30.dp))
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
            Button(onClick = { registerLayer(context, name, target) }) {
                Text(text = stringResource(id = R.string.add))
            }
            Spacer(modifier = Modifier.width(30.dp))
            Button(onClick = { enableLayer(context, info, name, target) }) {
                Text(text = stringResource(id = R.string.apply))
            }
            Spacer(modifier = Modifier.width(30.dp))
            Button(onClick = { disableLayer(context, info, name, target) }) {
                Text(text = stringResource(id = R.string.remove))
            }
        }
    }
}


//    .\adb shell sh /storage/emulated/0/Android/data/moe.shizuku.privileged.api/start.sh
//    .\adb shell cmd overlay disable --user current com.android.shell:com.strangerweather.rootlessuimods.android.test
//    .\adb shell cmd overlay list
