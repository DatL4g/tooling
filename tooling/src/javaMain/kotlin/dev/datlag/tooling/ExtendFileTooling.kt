package dev.datlag.tooling

import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.persistentSetOf
import kotlinx.collections.immutable.toImmutableSet
import java.io.File
import java.nio.file.FileSystems

/**
 * Get the home directory of the user.
 * Falls back to *HOME* system environment variable if JVM property *user.home* could not be found.
 *
 * @return a [File] of the user home directory.
 */
fun Tooling.homeDirectory(): File? {
    return systemProperty("user.home")?.let {
        File(it)
    } ?: systemEnv("HOME")?.let {
        File(it)
    } ?: systemEnv("\$HOME")?.let {
        File(it)
    }
}

/**
 * Provides all root directories available in the system.
 *
 * @return a [Set] of [File]s which are root directories.
 */
fun Tooling.findSystemRoots(): ImmutableSet<File> {
    val windowsRoot = systemEnv("SystemDrive")

    val defaultRoots = Tooling.onSupportsNio {
        scopeCatching {
            FileSystems.getDefault()?.rootDirectories?.mapNotNull {
                it?.toFile()
            }
        }.getOrNull()?.ifEmpty { null }
    } ?: scopeCatching {
        File.listRoots().filterNotNull()
    }.getOrNull()?.ifEmpty { null }


    val roots = (defaultRoots ?: persistentSetOf()).normalize()

    return (if (!windowsRoot.isNullOrBlank()) {
        roots.sortedByDescending {
            it.canonicalPath.trim().equals(windowsRoot, true) || it.isSame(File(windowsRoot))
        }.toImmutableSet()
    } else {
        roots
    })
}

expect fun Tooling.supportsNio(): Boolean

internal fun <T> Tooling.onSupportsNio(block: () -> T): T? {
    return if (supportsNio()) {
        block()
    } else {
        null
    }
}