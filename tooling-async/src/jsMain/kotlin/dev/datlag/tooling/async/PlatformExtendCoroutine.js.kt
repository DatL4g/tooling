package dev.datlag.tooling.async

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher

actual val Dispatchers.TargetMain: MainCoroutineDispatcher
    get() = Main
actual val Dispatchers.TargetIO: CoroutineDispatcher
    get() = Default
actual val Dispatchers.TargetDefault: CoroutineDispatcher
    get() = Default
actual val Dispatchers.Virtual: CoroutineDispatcher?
    get() = null