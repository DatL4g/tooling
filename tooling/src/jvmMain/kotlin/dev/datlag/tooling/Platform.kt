package dev.datlag.tooling

import org.apache.commons.lang3.SystemUtils
import java.util.*

actual data object Platform {

    actual val isAndroid: Boolean = false
    actual val isAndroidJvm: Boolean = false
    actual val isAndroidNative: Boolean = false
    actual val isJvm: Boolean = true
    actual val isDesktop: Boolean = true
    actual val isDesktopJvm: Boolean = true
    actual val isDesktopNative: Boolean = false
    actual val isIOS: Boolean = false
    actual val isTVOS: Boolean = false
    actual val isWatchOS: Boolean = false
    actual val isMacOS: Boolean by lazy {
        scopeCatching {
            currentOs.isMacOSX
        }.getOrNull() ?: false
    }
    actual val isMacOSJvm: Boolean by lazy {
        isMacOS
    }
    actual val isMacOSNative: Boolean = false
    actual val isJs: Boolean = false
    actual val isJsDefault: Boolean = false
    actual val isJsWasm: Boolean = false
    actual val isLinux: Boolean by lazy {
        scopeCatching {
            currentOs.isLinux
        }.getOrNull() ?: false
    }
    actual val isLinuxJvm: Boolean by lazy {
        isLinux
    }
    actual val isLinuxNative: Boolean = false
    actual val isWindows: Boolean by lazy {
        scopeCatching {
            currentOs.isWindows
        }.getOrNull() ?: false
    }
    actual val isWindowsJvm: Boolean by lazy {
        isWindows
    }
    actual val isWindowsNative: Boolean = false
    actual val isNative: Boolean = false

    private const val PROPERTY_OS_NAME = "os.name"
    private const val PROPERTY_OS_ARCH = "os.arch"

    private var osInfo: OSInfo? = null
    private var os: OS? = null
    private var arch: ARCH? = null

    /**
     * Get information about the current OS.
     *
     * @see OSInfo
     * @return [OSInfo]
     */
    @JvmStatic
    val currentPlatform: OSInfo
        @Throws(IllegalStateException::class)
        get() {
            osInfo?.let {
                return it
            }

            return OSInfo(currentOs, currentArch).also {
                osInfo = it
            }
        }

    /**
     * Get the current OS without information about the architecture
     *
     * @see OS
     * @return [OS]
     */
    @JvmStatic
    val currentOs: OS
        @Throws(IllegalStateException::class)
        get() {
            os?.let {
                return it
            }

            val osName = systemProperty(PROPERTY_OS_NAME)
            val os = osName?.let { OS.matching(it) } ?: OS.fromSystemUtils()

            return os ?: throw IllegalStateException("Could not get matching OS: $osName")
        }

    /**
     * Get the current architecture without information about the OS
     *
     * @see ARCH
     * @return [ARCH]
     */
    @JvmStatic
    val currentArch: ARCH
        @Throws(IllegalStateException::class)
        get() {
            arch?.let {
                return it
            }

            val osArch = systemProperty(PROPERTY_OS_ARCH)
            val arch = osArch?.let { ARCH.matching(it) }

            return arch ?: throw IllegalStateException("Could not get matching Arch: $osArch")
        }

    sealed class OS(open val name: String, private vararg val values: String) {
        data class MACOSX(override val name: String) : OS(name, "mac", "darwin", "osx")
        data class LINUX(override val name: String) : OS(name, "linux")
        data class WINDOWS(override val name: String) : OS(name, "win", "windows")

        internal fun matches(osName: String): Boolean {
            return this.values.any {
                osName.startsWith(it, true)
            } || this.values.contains(osName.lowercase(Locale.ENGLISH))
        }

        val isMacOSX: Boolean
            get() = this is MACOSX

        val isLinux: Boolean
            get() = this is LINUX

        val isWindows: Boolean
            get() = this is WINDOWS

        override fun toString(): String {
            return when {
                isMacOSX -> "MacOS"
                isLinux -> "Linux"
                isWindows -> "Windows"
                else -> "Unknown"
            }
        }

        companion object {
            internal fun matching(osName: String): OS? = when {
                MACOSX(osName).matches(osName) -> MACOSX(osName)
                LINUX(osName).matches(osName) -> LINUX(osName)
                WINDOWS(osName).matches(osName) -> WINDOWS(osName)
                else -> null
            }

            internal fun fromSystemUtils(): OS? = when {
                SystemUtils.IS_OS_WINDOWS -> WINDOWS(SystemUtils.OS_NAME)
                SystemUtils.IS_OS_MAC || SystemUtils.IS_OS_MAC_OSX -> MACOSX(SystemUtils.OS_NAME)
                SystemUtils.IS_OS_LINUX -> LINUX(SystemUtils.OS_NAME)
                else -> null
            }
        }
    }

    sealed class ARCH(open val arch: String, private vararg val values: String) {
        data class AMD64(override val arch: String) : ARCH(arch, "amd64", "x86_64", "x64")
        data class I386(override val arch: String) : ARCH(arch, "x32", "x86", "i386", "i486", "i586", "i686", "i786")
        data class ARM64(override val arch: String) : ARCH(arch, "arm64", "aarch64")
        data class ARM(override val arch: String) : ARCH(arch, "arm")

        internal fun matches(osArch: String): Boolean {
            return this.values.contains(osArch.lowercase(Locale.ENGLISH))
        }

        val isAMD64: Boolean
            get() = this is AMD64

        val isI386: Boolean
            get() = this is I386

        val isARM64: Boolean
            get() = this is ARM64

        val isARM: Boolean
            get() = this is ARM

        override fun toString(): String {
            return when {
                isAMD64 -> "x64"
                isI386 -> "x32"
                isARM64 -> "arm64"
                isARM -> "arm"
                else -> "Unknown"
            }
        }

        companion object {
            internal fun matching(osArch: String): ARCH? = when {
                AMD64(osArch).matches(osArch) -> AMD64(osArch)
                I386(osArch).matches(osArch) -> I386(osArch)
                ARM64(osArch).matches(osArch) -> ARM64(osArch)
                ARM(osArch).matches(osArch) -> ARM(osArch)
                else -> null
            }
        }
    }

    /**
     * Class holding information about a platform.
     *
     * @param os the basic OS info, whether it is [OS.LINUX], [OS.MACOSX] or [OS.WINDOWS] and it's name.
     * @param arch the Arch info, whether it is [ARCH.AMD64], [ARCH.I386], [ARCH.ARM64] or [ARCH.ARM] and it's name
     */
    data class OSInfo(
        val os: OS,
        val arch: ARCH
    )

}