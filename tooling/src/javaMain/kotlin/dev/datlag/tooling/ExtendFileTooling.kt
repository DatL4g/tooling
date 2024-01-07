package dev.datlag.tooling

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
fun Tooling.findSystemRoots(): Set<File> {
    val windowsRoot = systemEnv("SystemDrive")
    val roots = (scopeCatching {
        FileSystems.getDefault()?.rootDirectories?.mapNotNull {
            it?.toFile()
        }
    }.getOrNull()?.ifEmpty { null } ?: scopeCatching {
        File.listRoots().filterNotNull()
    }.getOrNull()?.ifEmpty { null } ?: emptyList()).normalize()

    return (if (!windowsRoot.isNullOrBlank()) {
        roots.sortedByDescending {
            it.canonicalPath.trim().equals(windowsRoot, true) || it.isSame(File(windowsRoot))
        }.toSet()
    } else {
        roots
    })
}