package dev.datlag.tooling.async

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

actual val Dispatchers.TargetMain: MainCoroutineDispatcher
    get() = Main
actual val Dispatchers.TargetIO: CoroutineDispatcher
    get() = IO
actual val Dispatchers.TargetDefault: CoroutineDispatcher
    get() = Default

private val virtualDispatcher: CoroutineDispatcher? by lazy {
    scopeCatching {
        Executors.newVirtualThreadPerTaskExecutor().asCoroutineDispatcher()
    }.getOrNull() ?: scopeCatching {
        (Executors::class.java.getMethod("newVirtualThreadPerTaskExecutor").invoke(null) as? ExecutorService)?.asCoroutineDispatcher()
    }.getOrNull()
}

actual val Dispatchers.Virtual: CoroutineDispatcher?
    get() = virtualDispatcher