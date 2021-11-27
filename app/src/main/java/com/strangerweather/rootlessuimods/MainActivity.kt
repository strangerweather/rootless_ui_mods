package com.strangerweather.rootlessuimods

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.strangerweather.rootlessuimods.components.BasicContent
import com.strangerweather.rootlessuimods.components.tabs.TabScreen
import com.strangerweather.rootlessuimods.content.ColorModsContent
import com.strangerweather.rootlessuimods.ui.theme.RootlessUIModsTheme
import com.strangerweather.rootlessuimods.utils.MainViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
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
                            ColorModsContent(
                                applicationContext = applicationContext,
                                applicationInfo = applicationInfo
                            )
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
