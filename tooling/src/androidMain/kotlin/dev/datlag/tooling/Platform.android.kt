package dev.datlag.tooling

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat
import androidx.core.net.toUri

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
    actual val isNative: Boolean = false

    /**
     * Check if the current platform is an AndroidTV.
     *
     * @param [packageManager] the required PackageManager to check if the current platform has TV system features.
     * @return whether the current platform is an AndroidTV.
     */
    @Suppress("DEPRECATION")
    fun isTelevision(packageManager: PackageManager): Boolean {
        val leanbackOnly = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            packageManager.hasSystemFeature(PackageManager.FEATURE_LEANBACK_ONLY)
        } else {
            false
        }

        return packageManager.hasSystemFeature(PackageManager.FEATURE_TELEVISION)
                || packageManager.hasSystemFeature(PackageManager.FEATURE_LEANBACK)
                || leanbackOnly
    }

    /**
     * Check if the current platform is an AndroidTV.
     *
     * @param [configuration] the required Configuration to check if the current platform uses TV UI mode.
     * @return whether the current platform is and AndroidTV
     */
    fun isTelevision(configuration: Configuration): Boolean {
        return (configuration.uiMode and Configuration.UI_MODE_TYPE_MASK) == Configuration.UI_MODE_TYPE_TELEVISION
    }

    /**
     * Check if the current platform is an AndroidTV.
     *
     * @param [context] the [Context] to get the required PackageManager or Configuration to check if the current platform has TV system features.
     * @return whether the current platform is an AndroidTV.
     */
    fun isTelevision(context: Context): Boolean {
        return isTelevision(
            context.packageManager ?: context.applicationContext.packageManager
        ) || context.resources.configuration?.let(::isTelevision) ?: false
    }

    /**
     * Check if the current platform is an AndroidWatch.
     *
     * @param [packageManager] the required PackageManager to check if the current platform has Watch system features.
     * @return whether the current platform is an AndroidWatch.
     */
    fun isWatch(packageManager: PackageManager): Boolean {
        return packageManager.hasSystemFeature(PackageManager.FEATURE_WATCH)
    }

    /**
     * Check if the current platform is an AndroidWatch.
     *
     * @param [configuration] the required Configuration to check if the current platform uses Watch UI mode.
     * @return whether the current platform is and AndroidWatch
     */
    fun isWatch(configuration: Configuration): Boolean {
        return (configuration.uiMode and Configuration.UI_MODE_TYPE_MASK) == Configuration.UI_MODE_TYPE_WATCH
    }

    /**
     * Check if the current platform is an AndroidWatch.
     *
     * @param [context] the [Context] to get the required PackageManager or Configuration to check if the current platform has Watch system features.
     * @return whether the current platform is an AndroidWatch.
     */
    fun isWatch(context: Context): Boolean {
        return isWatch(
            context.packageManager ?: context.applicationContext.packageManager
        ) || context.resources.configuration?.let(::isWatch) ?: false
    }

    /**
     * Open a given [Uri] in browser.
     *
     * @param [context] the [Context] used to open the uri in browser.
     * @return true if intent could be launched.
     */
    fun openInBrowser(uri: Uri, context: Context): Boolean {
        val browserIntent = Intent(Intent.ACTION_VIEW, uri)

        val firstResult = scopeCatching {
            ContextCompat.startActivity(context, browserIntent, null)
        }.isSuccess
        if (firstResult) {
            return true
        }

        val newIntent = browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        return scopeCatching {
            ContextCompat.startActivity(context, newIntent, null)
        }.isSuccess
    }

    /**
     * Open a given [String] in browser.
     *
     * @param [context] the [Context] used to open the uri in browser.
     * @return true if intent could be launched.
     */
    fun openInBrowser(uri: String, context: Context): Boolean = openInBrowser(uri.toUri(), context)
}