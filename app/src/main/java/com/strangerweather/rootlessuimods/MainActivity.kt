package com.strangerweather.rootlessuimods

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.strangerweather.rootlessuimods.components.HomePageButtons
import com.strangerweather.rootlessuimods.ui.theme.RootlessUIModsTheme
import com.strangerweather.rootlessuimods.utils.ColorCircle
import com.strangerweather.rootlessuimods.utils.ColorPicker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.lsposed.hiddenapibypass.HiddenApiBypass
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
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text("Rootless UI Mods") },
                            )
                        },
                        content = {
                            LazyColumn(
                                modifier = Modifier.fillMaxHeight(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                item {
                                    HomePageButtons(
                                        context = context,
                                        info = applicationInfo,
                                        name = "accent1100_6",
                                        target = "android"
                                    )
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}


//    .\adb shell sh /storage/emulated/0/Android/data/moe.shizuku.privileged.api/start.sh
//    .\adb shell cmd overlay disable --user current com.android.shell:com.strangerweather.rootlessuimods.android.test
//    .\adb shell cmd overlay list
