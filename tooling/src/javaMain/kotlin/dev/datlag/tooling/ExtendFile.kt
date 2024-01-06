package dev.datlag.tooling

import java.io.File
import java.io.RandomAccessFile
import java.nio.channels.FileChannel
import java.nio.file.Files
import java.nio.file.LinkOption
import java.util.stream.Collectors

fun File.readChannel(block: FileChannel.() -> Unit) {
    val reader = RandomAccessFile(this, "r")
    block(reader.channel)
    reader.close()
}

fun File.writeChannel(block: FileChannel.() -> Unit) {
    val writer = RandomAccessFile(this, "rw")
    block(writer.channel)
    writer.close()
}

@JvmOverloads
fun File?.existsSafely(default: Boolean = false): Boolean {
    if (this == null) {
        return false
    }

    return scopeCatching {
        Files.exists(this.toPath())
    }.getOrNull() ?: scopeCatching {
        this.exists()
    }.getOrNull() ?: default
}

@JvmOverloads
fun File.canReadSafely(default: Boolean = false): Boolean {
    return scopeCatching {
        Files.isReadable(this.toPath())
    }.getOrNull() ?: scopeCatching {
        this.canRead()
    }.getOrNull() ?: default
}

@JvmOverloads
fun File.canWriteSafely(default: Boolean = false): Boolean {
    return scopeCatching {
        Files.isWritable(this.toPath())
    }.getOrNull() ?: scopeCatching {
        this.canWrite()
    }.getOrNull() ?: default
}

@JvmOverloads
fun File?.existsRSafely(default: Boolean = false): Boolean {
    if (this == null) {
        return false
    }

    return existsSafely(default) && canReadSafely(default)
}

@JvmOverloads
fun File?.existsRWSafely(default: Boolean = false): Boolean {
    if (this == null) {
        return false
    }

    return existsSafely(default) && canReadSafely(default) && canWriteSafely()
}

@JvmOverloads
fun File.isSymlinkSafely(default: Boolean = false): Boolean {
    return scopeCatching {
        Files.isSymbolicLink(this.toPath())
    }.getOrNull() ?: scopeCatching {
        !Files.isRegularFile(this.toPath(), LinkOption.NOFOLLOW_LINKS)
    }.getOrNull() ?: default
}

fun File.getOriginalFile(): File {
    return if (isSymlinkSafely()) scopeCatching {
        Files.readSymbolicLink(this.toPath()).toFile()
    }.getOrNull() ?: this else this
}

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
            sourceFile.canonicalFile == targetFile.canonicalFile || Files.isSameFile(sourceFile.toPath(), targetFile.toPath())
        }.getOrNull() ?: default
    }
}

fun File.listFilesSafely(): List<File> {
    return (scopeCatching {
        Files.list(this.toPath()).collect(Collectors.toList()).mapNotNull { path ->
            path?.toFile()
        }
    }.getOrNull() ?: emptyList()).ifEmpty {
        scopeCatching {
            this.listFiles()
        }.getOrNull()?.filterNotNull() ?: emptyList()
    }
}

fun File.deleteSafely(): Boolean {
    return scopeCatching {
        Files.delete(this.toPath())
    }.isSuccess || scopeCatching {
        this.delete()
    }.getOrNull() ?: false
}

fun Collection<File>.normalize(): Set<File> {
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

@JvmOverloads
fun File.isDirectorySafely(default: Boolean = false): Boolean {
    return scopeCatching {
        Files.isDirectory(this.toPath())
    }.getOrNull() ?: scopeCatching {
        Files.isDirectory(this.toPath())
    }.getOrNull() ?: default
}

fun File.parentSafely(): File? {
    return scopeCatching {
        this.toPath().parent?.toFile()
    }.getOrNull() ?: scopeCatching {
        this.parentFile
    }.getOrNull()
}

@JvmOverloads
fun File.mkdirsSafely(default: Boolean = false): Boolean {
    return scopeCatching {
        Files.createDirectories(this.toPath())
    }.getOrNull()?.toFile()?.existsSafely() ?: scopeCatching {
        this.mkdirs()
    }.getOrNull() ?: default
}