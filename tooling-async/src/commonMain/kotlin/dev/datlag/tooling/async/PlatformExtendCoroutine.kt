package dev.datlag.tooling.async

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher

/**
 * The main dispatcher on the current platform.
 */
expect val Dispatchers.TargetMain: MainCoroutineDispatcher

/**
 * The IO dispatcher on the current platform.
 */
expect val Dispatchers.TargetIO: CoroutineDispatcher

/**
 * The default dispatcher on the current platform.
 */
expect val Dispatchers.TargetDefault: CoroutineDispatcher

/**
 * Virtual Dispatcher available on JVM.
 */
expect val Dispatchers.Virtual: CoroutineDispatcher?

/**
 * Virtual Dispatcher available on JVM or IO fallback.
 */
val Dispatchers.VirtualIO: CoroutineDispatcher
    get() = Virtual ?: TargetIO