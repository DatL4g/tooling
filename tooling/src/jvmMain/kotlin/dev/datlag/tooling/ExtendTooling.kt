package dev.datlag.tooling

import net.harawata.appdirs.AppDirs
import net.harawata.appdirs.AppDirsFactory
import net.harawata.appdirs.impl.MacOSXAppDirs
import net.harawata.appdirs.impl.ShellFolderResolver
import net.harawata.appdirs.impl.UnixAppDirs
import net.harawata.appdirs.impl.WindowsAppDirs
import java.awt.Toolkit
import java.io.File

/**
 * Apply [title] to the Application, this fixes appearance on some targets.
 *
 * @return true if the title could be applied.
 */
fun Tooling.applicationTitle(title: String): Boolean = scopeCatching {
    val toolkit = Toolkit.getDefaultToolkit()
    val awtAppClassNameField = toolkit.javaClass.getDeclaredField("awtAppClassName")
    val working = try {
        awtAppClassNameField.isAccessible = true
        awtAppClassNameField.canAccess(null)
    } catch (ignored: Throwable) {
        awtAppClassNameField.trySetAccessible()
    }
    awtAppClassNameField.set(toolkit, title)
    working
}.getOrNull() ?: false

private val dirs: AppDirs by lazy {
    scopeCatching {
        when (Platform.currentOs) {
            is Platform.OS.MACOSX -> MacOSXAppDirs()
            is Platform.OS.WINDOWS -> WindowsAppDirs(ShellFolderResolver())
            is Platform.OS.LINUX -> UnixAppDirs()
        }
    }.getOrNull() ?: AppDirsFactory.getInstance()
}

@JvmOverloads
fun Tooling.getFileRWInUserConfigDir(
    child: String?,
    appName: String,
    appVersion: String? = null,
    appAuthor: String? = null,
    roaming: Boolean = false,
    createIfNotExists: Boolean = false
): File {
    val normalizedChild = if (child.isNullOrBlank()) {
        null
    } else {
        child
    }
    val parentFile = File(dirs.getUserConfigDir(appName, appVersion, appAuthor, roaming))
    var returnFile = normalizedChild?.let { File(parentFile, it) } ?: parentFile

    if (returnFile.existsRWSafely()) {
        return returnFile
    }

    val parentExists = returnFile.parentExistsRWOrCreateSafely(parentFile)

    if (!parentExists && Platform.isLinux) {
        val configDir = File(homeDirectory(), ".config/$appName").apply {
            mkdirsSafely()
        }
        returnFile = normalizedChild?.let { File(configDir, it) } ?: configDir
    }

    if (createIfNotExists) {
        if (normalizedChild != null) {
            returnFile.createAsFileSafely()
        } else {
            returnFile.mkdirsSafely()
        }
    }
    return returnFile
}

@JvmOverloads
fun Tooling.getFileRWInUserDataDir(
    child: String?,
    appName: String,
    appVersion: String? = null,
    appAuthor: String? = null,
    roaming: Boolean = false,
    createIfNotExists: Boolean = false
): File {
    val normalizedChild = if (child.isNullOrBlank()) {
        null
    } else {
        child
    }
    val parentFile = File(dirs.getUserDataDir(appName, appVersion, appAuthor, roaming))
    var returnFile = normalizedChild?.let { File(parentFile, it) } ?: parentFile

    if (returnFile.existsRWSafely()) {
        return returnFile
    }

    val parentExists = returnFile.parentExistsRWOrCreateSafely(parentFile)

    if (!parentExists && Platform.isLinux) {
        val dataDir = File(homeDirectory(), ".local/share/$appName").apply {
            mkdirsSafely()
        }
        returnFile = normalizedChild?.let { File(dataDir, it) } ?: dataDir
    }

    if (createIfNotExists) {
        if (normalizedChild != null) {
            returnFile.createAsFileSafely()
        } else {
            returnFile.mkdirsSafely()
        }
    }
    return returnFile
}

@JvmOverloads
fun Tooling.getFileRWInSiteDataDir(
    child: String?,
    appName: String,
    appVersion: String? = null,
    appAuthor: String? = null,
    multiPath: Boolean = false,
    createIfNotExists: Boolean = false
): File {
    val normalizedChild = if (child.isNullOrBlank()) {
        null
    } else {
        child
    }
    val parentFile = File(dirs.getSiteDataDir(appName, appVersion, appAuthor, multiPath))
    var returnFile = normalizedChild?.let { File(parentFile, it) } ?: parentFile

    if (returnFile.existsRWSafely()) {
        return returnFile
    }

    val parentExists = returnFile.parentExistsRWOrCreateSafely(parentFile)

    if (!parentExists && Platform.isLinux) {
        val dataDir = File("/usr/local/share/$appName")
        returnFile = normalizedChild?.let { File(dataDir, it) } ?: dataDir

        if (returnFile.existsRWSafely()) {
            return returnFile
        }

        val newParentExists = returnFile.parentExistsRWOrCreateSafely(dataDir)

        if (!newParentExists) {
            val alternativeDataDir = File(homeDirectory(), ".local/share/flatpak/exports/share/$appName").apply {
                mkdirsSafely()
            }

            returnFile = normalizedChild?.let { File(alternativeDataDir, it) } ?: alternativeDataDir
        }
    }

    if (createIfNotExists) {
        if (normalizedChild != null) {
            returnFile.createAsFileSafely()
        } else {
            returnFile.mkdirsSafely()
        }
    }
    return returnFile
}

@JvmOverloads
fun Tooling.getComposeWriteableRootFolder(
    appName: String,
    appVersion: String? = null,
    appAuthor: String? = null,
    multiPath: Boolean = false,
): File {
    val resDir = systemProperty("compose.application.resources.dir")?.let { File(it) }
    return if (resDir.existsRWSafely()) {
        resDir!!
    } else {
        val site = getFileRWInSiteDataDir(
            child = null,
            appName = appName,
            appVersion = appVersion,
            appAuthor = appAuthor,
            multiPath = multiPath,
            createIfNotExists = false
        )

        if (site.existsRWSafely()) {
            site
        } else {
            File("./")
        }
    }
}