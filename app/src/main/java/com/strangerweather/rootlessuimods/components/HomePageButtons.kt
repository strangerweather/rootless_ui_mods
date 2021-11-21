package com.strangerweather.rootlessuimods.components

import android.content.Context
import android.content.pm.ApplicationInfo
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.strangerweather.rootlessuimods.functions.disableLayer
import com.strangerweather.rootlessuimods.functions.enableLayer
import com.strangerweather.rootlessuimods.utils.registerLayer
import com.strangerweather.rootlessuimods.R

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