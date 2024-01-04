package dev.datlag.tooling.compose

import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.res.useResource
import dev.datlag.tooling.Tooling
import dev.datlag.tooling.scopeCatching
import java.awt.Image
import java.io.InputStream

fun Tooling.loadAppIcon(
    window: ComposeWindow,
    vararg assets: Image?
) {
    window.iconImages = assets.filterNotNull()
}

fun Tooling.getResourcesAsInputStream(location: String): InputStream? {
    val classLoader = Tooling::class.java.classLoader ?: this::class.java.classLoader
    return scopeCatching {
        classLoader?.getResourceAsStream(location)
    }.getOrNull() ?: scopeCatching {
        Tooling::class.java.getResourceAsStream(location)
    }.getOrNull() ?: scopeCatching {
        this::class.java.getResourceAsStream(location)
    }.getOrNull() ?: scopeCatching {
        useResource(location) { it }
    }.getOrNull()
}