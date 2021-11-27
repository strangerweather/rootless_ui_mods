package com.strangerweather.rootlessuimods.functions

import android.content.Context
import android.content.pm.ApplicationInfo
import tk.zwander.fabricateoverlay.FabricatedOverlay
import tk.zwander.fabricateoverlay.FabricatedOverlayEntry
import tk.zwander.fabricateoverlay.OverlayAPI


fun registerLayer(context: Context, name: String, target: String, overlay: String, value: Int) {
    val overlayEntries = listOf(
        FabricatedOverlayEntry(
            resourceName = "$target:$overlay",
            resourceType = 28,
            resourceValue = value
        )
    )
    OverlayAPI.getInstance(context) { api ->
        api.registerFabricatedOverlay(
            FabricatedOverlay(
                "com.strangerweather.rootlessuimods.$target.$name",
                target
            ).apply {
                overlayEntries.forEach { overlay ->
                    entries[overlay.resourceName] = overlay
                }
            }
        )
    }
}

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

fun deleteLayer(
    context: Context,
    name: String,
    target: String
) {
    OverlayAPI.getInstance(context) { api ->
        api.unregisterFabricatedOverlay(
            FabricatedOverlay.generateOverlayIdentifier(
                "com.strangerweather.rootlessuimods.$target.$name"
            )
        )
    }
}