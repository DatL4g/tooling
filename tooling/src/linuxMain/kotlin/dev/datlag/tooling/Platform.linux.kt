package dev.datlag.tooling

actual object Platform {
    actual val isAndroid: Boolean = false
    actual val isAndroidJvm: Boolean = false
    actual val isAndroidNative: Boolean = false
    actual val isJvm: Boolean = false
    actual val isDesktop: Boolean = true
    actual val isDesktopJvm: Boolean = false
    actual val isDesktopNative: Boolean = true
    actual val isIOS: Boolean = false
    actual val isTVOS: Boolean = false
    actual val isWatchOS: Boolean = false
    actual val isMacOS: Boolean = false
    actual val isMacOSJvm: Boolean = false
    actual val isMacOSNative: Boolean = false
    actual val isJs: Boolean = false
    actual val isJsDefault: Boolean = false
    actual val isJsWasm: Boolean = false
    actual val isLinux: Boolean = true
    actual val isLinuxJvm: Boolean = false
    actual val isLinuxNative: Boolean = true
    actual val isWindows: Boolean = false
    actual val isWindowsJvm: Boolean = false
    actual val isWindowsNative: Boolean = false
}