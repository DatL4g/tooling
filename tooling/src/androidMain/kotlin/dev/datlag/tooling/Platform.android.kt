package dev.datlag.tooling

import android.content.Context
import android.content.pm.PackageManager

actual object Platform {
    actual val isAndroid: Boolean = true
    actual val isAndroidJvm: Boolean = true
    actual val isAndroidNative: Boolean = false
    actual val isJvm: Boolean = true
    actual val isDesktop: Boolean = false
    actual val isDesktopJvm: Boolean = false
    actual val isDesktopNative: Boolean = false
    actual val isIOS: Boolean = false
    actual val isTVOS: Boolean = false
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

    /**
     * Check if the current platform is an AndroidTV.
     *
     * @param [packageManager] the required PackageManager to check if the current platform has TV system features.
     * @return whether the current platform is an AndroidTV.
     */
    @Suppress("DEPRECATION")
    fun isTelevision(packageManager: PackageManager): Boolean {
        return packageManager.hasSystemFeature(PackageManager.FEATURE_TELEVISION)
                || packageManager.hasSystemFeature(PackageManager.FEATURE_LEANBACK)
                || packageManager.hasSystemFeature(PackageManager.FEATURE_LEANBACK_ONLY)
    }

    /**
     * Check if the current platform is an AndroidTV.
     *
     * @param [context] the [Context] to get the required PackageManager to check if the current platform has TV system features.
     * @return whether the current platform is an AndroidTV.
     */
    @Suppress("DEPRECATION")
    fun isTelevision(context: Context): Boolean {
        return isTelevision(context.packageManager ?: context.applicationContext.packageManager)
    }
}