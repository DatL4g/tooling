package dev.datlag.tooling.compose

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher

actual val Dispatchers.TargetMain: MainCoroutineDispatcher
    get() = Main
actual val Dispatchers.TargetIO: CoroutineDispatcher
    get() = IO
actual val Dispatchers.TargetDefault: CoroutineDispatcher
    get() = Default