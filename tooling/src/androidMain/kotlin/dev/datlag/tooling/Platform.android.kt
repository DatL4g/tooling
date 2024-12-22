package dev.datlag.tooling

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import androidx.car.app.connection.CarConnection
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
     * Check if the current platform is an Android Automotive.
     *
     * @param packageManager the required PackageManager to check if the current platform has Automotive system features.
     * @return whether the current platform is an Android Automotive.
     */
    fun isCar(packageManager: PackageManager): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            packageManager.hasSystemFeature(PackageManager.FEATURE_AUTOMOTIVE)
        } else {
            false
        }
    }

    /**
     * **!!! Fails on Android 12+ !!!**
     *
     * Check if the current platform is an Android Automotive.
     *
     * @param configuration the required Configuration to check if the current platform uses Car UI mode.
     * @return whether the current platform is an Android Automotive.
     */
    fun isCar(configuration: Configuration): Boolean {
        return (configuration.uiMode and Configuration.UI_MODE_TYPE_MASK) == Configuration.UI_MODE_TYPE_CAR
    }

    /**
     * Check if the current platform is an Android Auto(motive).
     *
     * @param context the Context to get the required PackageManager or Configuration to check if the current platform has Automotive system features.
     * @param connection the current CarConnection to detect all types like projection. You should provide a singleton to ensure up-to-date data or handle the connection yourself.
     * @param detectProjection whether or not Android Auto (projection to car head unit) should be detected.
     * @return whether the current platform is an Android Auto(motive).
     */
    @JvmOverloads
    fun isCar(
        context: Context,
        connection: CarConnection = CarConnection(context),
        detectProjection: Boolean = false
    ): Boolean {
        fun checkConnection() = when (connection.type.value) {
            CarConnection.CONNECTION_TYPE_NOT_CONNECTED -> false
            CarConnection.CONNECTION_TYPE_NATIVE -> true
            CarConnection.CONNECTION_TYPE_PROJECTION -> detectProjection
            else -> false
        }

        return isCar(
            context.packageManager ?: context.applicationContext.packageManager
        ) || (context.resources.configuration?.let(::isCar) ?: false) || checkConnection()
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
            context.startActivity(browserIntent, null)
        }.isSuccess
        if (firstResult) {
            return true
        }

        val newIntent = browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        return scopeCatching {
            context.startActivity(newIntent, null)
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