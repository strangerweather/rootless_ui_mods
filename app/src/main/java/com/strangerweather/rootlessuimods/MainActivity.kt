package com.strangerweather.rootlessuimods

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.strangerweather.rootlessuimods.ui.theme.RootlessUIModsTheme
import rikka.shizuku.Shizuku
import rikka.shizuku.Shizuku.checkSelfPermission

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val shizukuAvailable = Shizuku.pingBinder()
        println("shizukuAvailable = $shizukuAvailable")

        setContent {
            RootlessUIModsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    if (!shizukuAvailable) ShowShizukuDialog() else checkShizukuPermission()
                    if (checkShizukuPermission()) {
                        Text(text = "Victory!")
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
    println("isGranted = $isGranted")
    return isGranted
}