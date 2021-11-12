package com.strangerweather.rootlessuimods

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.strangerweather.rootlessuimods.ui.theme.RootlessUIModsTheme
import org.lsposed.hiddenapibypass.HiddenApiBypass
import rikka.shizuku.Shizuku
import rikka.shizuku.Shizuku.checkSelfPermission
import rikka.shizuku.ShizukuProvider
import kotlin.streams.toList


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        HiddenApiBypass.setHiddenApiExemptions("L")

        val shizukuAvailable = Shizuku.pingBinder()
        println("shizukuAvailable = $shizukuAvailable")

        ShizukuProvider.enableMultiProcessSupport(true)

        setContent {
            RootlessUIModsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    if (!shizukuAvailable) ShowShizukuDialog() else checkShizukuPermission()
                    if (checkShizukuPermission()) {
                        AdbTest()
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


fun checkShizukuPermission(): Boolean {
    val isGranted = if (Shizuku.isPreV11() || Shizuku.getVersion() < 11) {
        checkSelfPermission() == PackageManager.PERMISSION_GRANTED
    } else {
        checkSelfPermission() == PackageManager.PERMISSION_GRANTED
    }
    return isGranted
}

@Composable
fun AdbTest() {
    val context = LocalContext.current

    val installedAppsList = context.packageManager.getInstalledApplications(0).toList()

    LazyColumn{item { Text(text = installedAppsList.toString()) }}
}



