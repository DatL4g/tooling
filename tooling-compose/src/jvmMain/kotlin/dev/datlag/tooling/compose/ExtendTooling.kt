package dev.datlag.tooling.compose

import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.res.useResource
import dev.datlag.tooling.Tooling
import dev.datlag.tooling.scopeCatching
import java.awt.Image
import java.io.InputStream
import kotlin.reflect.KClass

/**
 * Easy method to load images as app icon on a [ComposeWindow].
 *
 * @param [window] the [ComposeWindow] where the images will be applied.
 * @param [assets] a nullable (list of) [Image] use.
 */
fun Tooling.loadAppIcon(
    window: ComposeWindow,
    vararg assets: Image?
) {
    window.iconImages = assets.filterNotNull()
}

/**
 * Load something from resources easily.
 *
 * @param [clazz] any [KClass] within your project.
 * @param [location] the location of the resource you want.
 * @return an [InputStream] of your resource or null if unable to retrieve.
 */
fun Tooling.getResourcesAsInputStream(clazz: KClass<*>, location: String): InputStream? {
    val classLoader = clazz.java.classLoader ?: Tooling::class.java.classLoader ?: this::class.java.classLoader
    return scopeCatching {
        classLoader?.getResourceAsStream(location)
    }.getOrNull() ?: scopeCatching {
        clazz.java.getResourceAsStream(location)
    }.getOrNull() ?: scopeCatching {
        Tooling::class.java.getResourceAsStream(location)
    }.getOrNull() ?: scopeCatching {
        this::class.java.getResourceAsStream(location)
    }.getOrNull() ?: scopeCatching {
        useResource(location) { it }
    }.getOrNull()
}