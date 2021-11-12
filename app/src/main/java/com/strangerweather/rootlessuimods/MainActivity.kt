package com.strangerweather.rootlessuimods

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.IBinder
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
import org.lsposed.hiddenapibypass.HiddenApiBypass
import rikka.shizuku.Shizuku
import rikka.shizuku.Shizuku.checkSelfPermission
import rikka.shizuku.ShizukuBinderWrapper
import rikka.shizuku.ShizukuProvider
import rikka.shizuku.SystemServiceHelper.getSystemService
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
                        accessApis()
//                        AdbTest()
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

@SuppressLint("PrivateApi")
fun accessApis() {
    val iPmClass = Class.forName("android.content.om.IOverlayManager")
    val iPmStub = Class.forName("android.content.om.IOverlayManager\$Stub")
    val asInterfaceMethod = iPmStub.getMethod("asInterface", IBinder::class.java)
    val grantRuntimePermissionMethod = iPmClass.getMethod(
        "grantRuntimePermission",
        String::class.java /* package name */,
        String::class.java /* permission name */,
        Int::class.java /* user ID */
    )

    val iPmInstance = asInterfaceMethod.invoke(
        null,
        ShizukuBinderWrapper(getSystemService("overlay"))
    )

    grantRuntimePermissionMethod.invoke(
        iPmInstance, "com.strangerweather.rootlessuimods",
        android.Manifest.permission.WRITE_SECURE_SETTINGS, 0
    )
}

@Composable
fun AdbTest() {


    val command = "cmd overlay list"

//    val command = "cmd overlay fabricate com.android.systemui:integer/quick_settings_num_columns 3"
//
    ProcessBuilder().command("sh", "-c", command).start().run {
        val lines = inputStream.bufferedReader().lines().toList()
        destroy()

        Text(text = lines.toString())
    }
}


