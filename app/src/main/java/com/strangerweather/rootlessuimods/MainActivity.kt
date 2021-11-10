package com.strangerweather.rootlessuimods

import android.annotation.SuppressLint
import android.content.Context
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
import moe.shizuku.server.IShizukuService
import org.lsposed.hiddenapibypass.HiddenApiBypass
import rikka.shizuku.Shizuku
import rikka.shizuku.Shizuku.checkSelfPermission
import rikka.shizuku.ShizukuBinderWrapper
import rikka.shizuku.SystemServiceHelper
import java.io.BufferedReader
import java.io.InputStreamReader


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        HiddenApiBypass.setHiddenApiExemptions("L")

        val shizukuAvailable = Shizuku.pingBinder()
        println("shizukuAvailable = $shizukuAvailable")

        setContent {
            RootlessUIModsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val context = applicationContext
                    if (!shizukuAvailable) ShowShizukuDialog() else checkShizukuPermission()
                    if (checkShizukuPermission()) {
                        accessApis()
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

@SuppressLint("PrivateApi")
fun accessApis(){
    val iPmClass = Class.forName("android.content.pm.IPackageManager")
    val iPmStub = Class.forName("android.content.pm.IPackageManager\$Stub")
    val asInterfaceMethod = iPmStub.getMethod("asInterface", IBinder::class.java)
    val grantRuntimePermissionMethod = iPmClass.getMethod("grantRuntimePermission", String::class.java /* package name */, String::class.java /* permission name */, Int::class.java /* user ID */)

    val iPmInstance = asInterfaceMethod.invoke(null, ShizukuBinderWrapper(SystemServiceHelper.getSystemService("package")))

    grantRuntimePermissionMethod.invoke(iPmInstance, "com.strangerweather.rootlessuimods", android.Manifest.permission.WRITE_SECURE_SETTINGS, 0)
}

@Composable
fun AdbTest() {
    val command = ""
    val process = Runtime.getRuntime().exec(command)
    process.waitFor()
    val reader = BufferedReader(
        InputStreamReader(process.inputStream)
    )
    val output = reader.readLines()
   Text(text = output.toString())
}