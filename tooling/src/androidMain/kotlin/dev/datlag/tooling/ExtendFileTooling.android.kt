package dev.datlag.tooling

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O)
actual fun Tooling.supportsNio(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
}