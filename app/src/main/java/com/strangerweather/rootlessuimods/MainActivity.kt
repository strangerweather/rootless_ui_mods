package com.strangerweather.rootlessuimods

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.google.accompanist.pager.ExperimentalPagerApi
import com.strangerweather.rootlessuimods.components.TabScreen
import com.strangerweather.rootlessuimods.ui.theme.RootlessUIModsTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.lsposed.hiddenapibypass.HiddenApiBypass
import tk.zwander.fabricateoverlay.ShizukuUtils


@ExperimentalGraphicsApi
class MainActivity : ComponentActivity() {
    @ExperimentalPagerApi
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


    @ExperimentalPagerApi
    @ExperimentalGraphicsApi
    private fun setAppContent() {
        setContent {
            RootlessUIModsTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text(stringResource(id = R.string.title)) },
                            )
                        },
                        content = {
                            TabScreen()
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
