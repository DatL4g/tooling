package dev.datlag.tooling

import java.io.File
import java.nio.file.Files
import java.nio.file.LinkOption
import java.util.stream.Collectors
import kotlin.coroutines.cancellation.CancellationException

/**
 * Extension method to check if a file exists without throwing an exception, except [CancellationException] to cancel parent jobs.
 *
 * @return whether a file or directory exists.
 */
@JvmOverloads
fun File?.existsSafely(default: Boolean = false): Boolean {
    if (this == null) {
        return false
    }

    return Tooling.onSupportsNio {
        scopeCatching {
            Files.exists(this.toPath())
        }.getOrNull()
    } ?: scopeCatching {
        this.exists()
    }.getOrNull() ?: default
}

/**
 * Extension method to check if a file can be read without throwing an exception, except [CancellationException] to cancel parent jobs.
 *
 * @return whether a file or directory can be read.
 */
@JvmOverloads
fun File.canReadSafely(default: Boolean = false): Boolean {
    return Tooling.onSupportsNio {
        scopeCatching {
            Files.isReadable(this.toPath())
        }.getOrNull()
    } ?: scopeCatching {
        this.canRead()
    }.getOrNull() ?: default
}

/**
 * Extension method to check if a file can be written without throwing an exception, except [CancellationException] to cancel parent jobs.
 *
 * @return whether a file or directory can be written.
 */
@JvmOverloads
fun File.canWriteSafely(default: Boolean = false): Boolean {
    return Tooling.onSupportsNio {
        scopeCatching {
            Files.isWritable(this.toPath())
        }.getOrNull()
    } ?: scopeCatching {
        this.canWrite()
    }.getOrNull() ?: default
}

/**
 * Extension method to check if a file exists and can be read without throwing an exception, except [CancellationException] to cancel parent jobs.
 *
 * @return whether a file or directory exists and can be read.
 */
@JvmOverloads
fun File?.existsRSafely(default: Boolean = false): Boolean {
    if (this == null) {
        return false
    }

    return existsSafely(default) && canReadSafely(default)
}

/**
 * Extension method to check if a file exists and can be written without throwing an exception, except [CancellationException] to cancel parent jobs.
 *
 * @return whether a file or directory exists and can be written.
 */
@JvmOverloads
fun File?.existsRWSafely(default: Boolean = false): Boolean {
    if (this == null) {
        return false
    }

    return existsSafely(default) && canReadSafely(default) && canWriteSafely()
}

/**
 * Extension method to check if a file is a symlink without throwing an exception, except [CancellationException] to cancel parent jobs.
 *
 * @return whether a file or directory is a symlink.
 */
@JvmOverloads
fun File.isSymlinkSafely(default: Boolean = false): Boolean {
    return Tooling.onSupportsNio {
        scopeCatching {
            Files.isSymbolicLink(this.toPath())
        }.getOrNull()
    } ?: Tooling.onSupportsNio {
        scopeCatching {
            !Files.isRegularFile(this.toPath(), LinkOption.NOFOLLOW_LINKS)
        }.getOrNull()
    } ?: default
}

/**
 * Extension method to get the real file of a symlink without throwing an exception, except [CancellationException] to cancel parent jobs.
 *
 * @return the real [File] of a symlink or itself.
 */
fun File.getOriginalFile(): File {
    return if (isSymlinkSafely()) {
        Tooling.onSupportsNio {
            scopeCatching {
                Files.readSymbolicLink(this.toPath()).toFile()
            }.getOrNull()
        } ?: this
    } else this
}

/**
 * Extension method to check if two files are the same (symlink aware) without throwing an exception, except [CancellationException] to cancel parent jobs.
 *
 * @return whether two files are the same.
 */
@JvmOverloads
fun File.isSame(file: File?, default: Boolean = false): Boolean {
    var sourceFile = this.getOriginalFile()
    if (!sourceFile.existsSafely()) {
        sourceFile = this
    }

    var targetFile = file?.getOriginalFile() ?: file
    if (!targetFile.existsSafely()) {
        targetFile = file
    }

    return if (targetFile == null) {
        false
    } else {
        this == targetFile || scopeCatching {
            sourceFile.canonicalFile == targetFile.canonicalFile || Tooling.onSupportsNio {
                Files.isSameFile(sourceFile.toPath(), targetFile.toPath())
            } ?: default
        }.getOrNull() ?: default
    }
}

/**
 * Extension method to get all children without throwing an exception, except [CancellationException] to cancel parent jobs.
 *
 * @return a [List] of [File]s available in provided parent.
 */
fun File.listFilesSafely(): List<File> {
    val nioFiles = Tooling.onSupportsNio {
        scopeCatching {
            Files.list(this.toPath()).collect(Collectors.toList()).mapNotNull { path ->
                path?.toFile()
            }
        }.getOrNull()
    } ?: emptyList()
    return nioFiles.ifEmpty {
        scopeCatching {
            this.listFiles()
        }.getOrNull()?.filterNotNull() ?: emptyList()
    }
}

/**
 * Extension method to delete a file safely without throwing an exception, except [CancellationException] to cancel parent jobs.
 *
 * @return whether the file could be deleted or not.
 */
@JvmOverloads
fun File.deleteSafely(default: Boolean = false): Boolean {
    return (Tooling.onSupportsNio {
        scopeCatching {
            Files.delete(this.toPath())
        }.isSuccess
    } ?: default) || scopeCatching {
        this.delete()
    }.getOrNull() ?: default
}

/**
 * Extension method to get a normalized [Set] of [File]s (symlink aware) without throwing an exception, except [CancellationException] to cancel parent jobs.
 *
 * @return a [Set] of unique [File]s.
 */
fun Iterable<File>.normalize(): Set<File> {
    val list = mutableSetOf<File>()
    this.forEach { file ->
        var realFile = file.getOriginalFile()
        if (!realFile.existsSafely()) {
            if (file.existsSafely()) {
                realFile = file
            } else {
                return@forEach
            }
        }
        if (list.firstOrNull { it.isSame(realFile) } == null) {
            list.add(realFile)
        }
    }
    return list
}

/**
 * Extension method to check if a file is a directory without throwing an exception, except [CancellationException] to cancel parent jobs.
 *
 * @return whether the file is a directory.
 */
@JvmOverloads
fun File.isDirectorySafely(default: Boolean = false): Boolean {
    return Tooling.onSupportsNio {
        scopeCatching {
            Files.isDirectory(this.toPath())
        }.getOrNull()
    } ?: scopeCatching {
        this.isDirectory
    }.getOrNull() ?: default
}

/**
 * Extension method to get the parent of a file exists without throwing an exception, except [CancellationException] to cancel parent jobs.
 *
 * @return the parent [File] or null.
 */
fun File.parentSafely(): File? {
    return Tooling.onSupportsNio {
        scopeCatching {
            this.toPath().parent?.toFile()
        }.getOrNull()
    } ?: scopeCatching {
        this.parentFile
    }.getOrNull()
}

/**
 * Extension method to create this and all parent directories without throwing an exception, except [CancellationException] to cancel parent jobs.
 *
 * @return whether all directories could be created.
 */
@JvmOverloads
fun File.mkdirsSafely(default: Boolean = false): Boolean {
    return Tooling.onSupportsNio {
        scopeCatching {
            Files.createDirectories(this.toPath())
        }.getOrNull()?.toFile()?.existsSafely()
    } ?: scopeCatching {
        this.mkdirs()
    }.getOrNull() ?: default
}

/**
 * Extension method to create a file without throwing an exception, except [CancellationException] to cancel parent jobs.
 *
 * @return whether the file could be created.
 */
@JvmOverloads
fun File.createAsFileSafely(default: Boolean = false): Boolean {
    if (this.existsSafely()) {
        return default
    }

    this.parentExistsOrCreateSafely()

    return Tooling.onSupportsNio {
        scopeCatching {
            Files.createFile(this.toPath())
        }.getOrNull()?.toFile()?.existsSafely()
    } ?: scopeCatching {
        this.createNewFile()
    }.getOrNull() ?: default
}

/**
 * Extension method to check if a parent file exists and creates if not without throwing an exception, except [CancellationException] to cancel parent jobs.
 *
 * @return whether the parent exists or could be created.
 */
@JvmOverloads
fun File.parentExistsOrCreateSafely(fallbackParent: File? = null): Boolean {
    return (this.parentSafely() ?: fallbackParent).existsSafely()
            || ((this.parentSafely() ?: fallbackParent)?.mkdirsSafely() == true && (this.parentSafely() ?: fallbackParent).existsSafely())
}

/**
 * Extension method to check if a parent file exists readable and creates if not without throwing an exception, except [CancellationException] to cancel parent jobs.
 *
 * @return whether the parent exists readable or could be created.
 */
@JvmOverloads
fun File.parentExistsROrCreateSafely(fallbackParent: File? = null): Boolean {
    return (this.parentSafely() ?: fallbackParent).existsRSafely()
            || ((this.parentSafely() ?: fallbackParent)?.mkdirsSafely() == true && (this.parentSafely() ?: fallbackParent).existsRSafely())
}

/**
 * Extension method to check if a parent file exists read-write able and creates if not without throwing an exception, except [CancellationException] to cancel parent jobs.
 *
 * @return whether the parent exists read-write able or could be created.
 */
@JvmOverloads
fun File.parentExistsRWOrCreateSafely(fallbackParent: File? = null): Boolean {
    return (this.parentSafely() ?: fallbackParent).existsRWSafely()
            || ((this.parentSafely() ?: fallbackParent)?.mkdirsSafely() == true && (this.parentSafely() ?: fallbackParent).existsRWSafely())
}