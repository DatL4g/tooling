package dev.datlag.tooling.decompose.lifecycle

import androidx.compose.runtime.compositionLocalOf
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.lifecycle.LifecycleRegistry

/**
 * [LifecycleOwner] holder to use [collectAsStateWithLifecycle].
 */
val LocalLifecycleOwner = compositionLocalOf<LifecycleOwner> {
    object : LifecycleOwner {
        override val lifecycle: Lifecycle = LifecycleRegistry()
    }
}