package dev.datlag.tooling

actual object Platform {
    actual val isAndroid: Boolean = false
    actual val isAndroidJvm: Boolean = false
    actual val isAndroidNative: Boolean = false
    actual val isJvm: Boolean = false
    actual val isDesktop: Boolean = false
    actual val isDesktopJvm: Boolean = false
    actual val isDesktopNative: Boolean = false
    actual val isIOS: Boolean = false
    actual val isTVOS: Boolean = true
    actual val isWatchOS: Boolean = false
    actual val isMacOS: Boolean = false
    actual val isMacOSJvm: Boolean = false
    actual val isMacOSNative: Boolean = false
    actual val isJs: Boolean = false
    actual val isJsDefault: Boolean = false
    actual val isJsWasm: Boolean = false
    actual val isLinux: Boolean = false
    actual val isLinuxJvm: Boolean = false
    actual val isLinuxNative: Boolean = false
    actual val isWindows: Boolean = false
    actual val isWindowsJvm: Boolean = false
    actual val isWindowsNative: Boolean = false
}