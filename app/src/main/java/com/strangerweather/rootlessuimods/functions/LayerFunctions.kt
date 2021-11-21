package com.strangerweather.rootlessuimods.functions

import android.content.Context
import android.content.pm.ApplicationInfo
import tk.zwander.fabricateoverlay.FabricatedOverlay
import tk.zwander.fabricateoverlay.OverlayAPI

fun enableLayer(context: Context, info: ApplicationInfo, name: String, target: String) {
    OverlayAPI.getInstance(context) { api ->
        api.setEnabled(
            FabricatedOverlay.generateOverlayIdentifier(
                "com.strangerweather.rootlessuimods.$target.$name",
                "com.android.shell"
            ), info.enabled, 0
        )
    }
}

fun disableLayer(
    context: Context,
    info: ApplicationInfo,
    name: String,
    target: String
) {
    OverlayAPI.getInstance(context) { api ->
        api.setEnabled(
            FabricatedOverlay.generateOverlayIdentifier(
                "com.strangerweather.rootlessuimods.$target.$name",
                "com.android.shell"
            ), !info.enabled, 0
        )
    }
}