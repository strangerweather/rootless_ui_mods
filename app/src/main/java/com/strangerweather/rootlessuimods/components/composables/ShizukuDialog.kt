package com.strangerweather.rootlessuimods.components.composables

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.strangerweather.rootlessuimods.R
import com.strangerweather.rootlessuimods.ui.theme.OrangeLight


@Composable
fun ShizukuDialog() {
    val openDialog = remember { mutableStateOf(true) }

    val activity = (LocalContext.current as? Activity)

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { },
            title = { },
            text = {
                DialogScreen()
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { activity?.finish() }
                    ) {
                        Text("Dismiss")
                    }
                }
            }
        )
    }
}

@Composable
fun DialogScreen() {
    val intent = remember {
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://play.google.com/store/apps/details?id=moe.shizuku.privileged.api")
        )
    }
    val context = LocalContext.current

    Column(
        Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.home_screen_title),
            style = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(20.dp)
                .border(
                    width = 0.5.dp,
                    color = (if (isSystemInDarkTheme()) Color.White else Color.Black)
                )
                .padding(20.dp),
        )
        Text(
            text = stringResource(id = R.string.home_screen_text1),
            Modifier.padding(start = 20.dp, end = 20.dp),
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .clickable { context.startActivity(intent) }
                .border(width = 2.dp, color = OrangeLight)
                .padding(20.dp),
            text = "Get Shizuku",
            color = OrangeLight,
            fontSize = 20.sp
        )
        Text(
            text = stringResource(id = R.string.home_screen_credits),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 100.dp)
        )
    }
}
