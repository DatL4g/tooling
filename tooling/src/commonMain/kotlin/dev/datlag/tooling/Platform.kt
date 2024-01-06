package dev.datlag.tooling

expect object Platform {
    val isAndroid: Boolean
    val isAndroidJvm: Boolean
    val isAndroidNative: Boolean
    val isJvm: Boolean
    val isDesktop: Boolean
    val isDesktopJvm: Boolean
    val isDesktopNative: Boolean
    val isIOS: Boolean
    val isTVOS: Boolean
    val isWatchOS: Boolean
    val isMacOS: Boolean
    val isMacOSJvm: Boolean
    val isMacOSNative: Boolean
    val isJs: Boolean
    val isJsDefault: Boolean
    val isJsWasm: Boolean
    val isLinux: Boolean
    val isLinuxJvm: Boolean
    val isLinuxNative: Boolean
    val isWindows: Boolean
    val isWindowsJvm: Boolean
    val isWindowsNative: Boolean
}