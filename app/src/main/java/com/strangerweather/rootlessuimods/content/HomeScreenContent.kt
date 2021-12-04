package com.strangerweather.rootlessuimods.content

import android.content.Context
import android.content.pm.ApplicationInfo
import android.util.TypedValue
import android.widget.ToggleButton
import androidx.compose.foundation.layout.*
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.strangerweather.rootlessuimods.R
import com.strangerweather.rootlessuimods.functions.*
import com.strangerweather.rootlessuimods.ui.theme.OrangeDark
import com.strangerweather.rootlessuimods.ui.theme.OrangeLight
import com.strangerweather.rootlessuimods.ui.theme.OrangeMedium
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun HomeScreen() {

    Column(Modifier.padding(20.dp)) {
        Text(
            text = stringResource(id = R.string.home_screen_title), style = TextStyle(
                fontWeight = FontWeight.Bold, fontSize = 20.sp,
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = stringResource(id = R.string.home_screen_text))
        Spacer(modifier = Modifier.height(20.dp))
        MonetSwitch()
    }
}

@Composable
fun MonetSwitch() {
    val checkedState = remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    val applicationContext = LocalContext.current
    val applicationInfo = ApplicationInfo()
    val name = "flag_monet"
    val target = "com.android.systemui"
    val overlay = "bool/flag_monet"
    val type = TypedValue.TYPE_INT_BOOLEAN
    val value = 0

    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "Monet Off")
        Switch(
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = it

                if (!checkedState.value) {
                    coroutineScope.launch {
                        monetOffLayer(
                            applicationContext = applicationContext,
                            applicationInfo = applicationInfo,
                            name = name,
                            target = target,
                            overlay = overlay,
                            type = type,
                            value = value
                        )
                    }
                } else {
                    coroutineScope.launch {
                        removeAndDelete(
                            context = applicationContext,
                            info = applicationInfo,
                            name = name,
                            target = target
                        )
                    }
                }
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = OrangeDark,
                checkedTrackColor = OrangeDark,
                uncheckedThumbColor = OrangeLight,
                uncheckedTrackColor = OrangeLight
            ),
        )
        Text(text = "Monet On")
    }
}

suspend fun monetOffLayer(
    applicationContext: Context,
    applicationInfo: ApplicationInfo,
    name: String,
    target: String,
    overlay: String,
    type: Int,
    value: Int
) = coroutineScope {
    registerLayer(
        context = applicationContext,
        name = name,
        target = target,
        overlay = overlay,
        type = type,
        value = value
    )
    enableLayer(
        context = applicationContext,
        info = applicationInfo,
        name = name,
        target = target
    )
}
