package dev.datlag.tooling

/**
 * This singleton class provides information about the current platform.
 */
expect object Platform {

    /**
     * Whether the current platform is Android (including Android Native)
     */
    val isAndroid: Boolean

    /**
     * Whether the current platform is Android on the JVM (excluding Native)
     */
    val isAndroidJvm: Boolean

    /**
     * Whether the current platform is Android Native (excluding JVM)
     */
    val isAndroidNative: Boolean

    /**
     * Whether the current platform is the JVM (including Desktop and Android JVM)
     */
    val isJvm: Boolean

    /**
     * Whether the current platform is Desktop (including Native targets)
     */
    val isDesktop: Boolean

    /**
     * Whether the current platform is Desktop on the JVM (excluding Native targets)
     */
    val isDesktopJvm: Boolean

    /**
     * Whether the current platform is Desktop Native (including Mac, Linux and Windows, excluding JVM)
     */
    val isDesktopNative: Boolean

    /**
     * Whether the current platform is iOS
     */
    val isIOS: Boolean

    /**
     * Whether the current platform is tvOS
     */
    val isTVOS: Boolean

    /**
     * Whether the current platform is watchOS
     */
    val isWatchOS: Boolean

    /**
     * Whether the current platform is Mac (including JVM and Native target)
     */
    val isMacOS: Boolean

    /**
     * Whether the current platform is Mac on the JVM (excluding Native target)
     */
    val isMacOSJvm: Boolean

    /**
     * Whether the current platform is Mac Native (excluding JVM)
     */
    val isMacOSNative: Boolean

    /**
     * Whether the current platform is Javascript (including "normal" JS and WASM)
     */
    val isJs: Boolean

    /**
     * Whether the current platform is "normal" Javascript
     */
    val isJsDefault: Boolean

    /**
     * Whether the current platform is WASM Javascript
     */
    val isJsWasm: Boolean

    /**
     * Whether the current platform is Linux (including JVM and Native target)
     */
    val isLinux: Boolean

    /**
     * Whether the current platform is Linux on the JVM (excluding Native target)
     */
    val isLinuxJvm: Boolean

    /**
     * Whether the current platform is Linux Native (excluding JVM)
     */
    val isLinuxNative: Boolean

    /**
     * Whether the current platform is Windows (including JVM and Native target)
     */
    val isWindows: Boolean

    /**
     * Whether the current platform is Windows on the JVM (excluding Native target)
     */
    val isWindowsJvm: Boolean

    /**
     * Whether the current platform is Windows Native (excluding JVM)
     */
    val isWindowsNative: Boolean
}