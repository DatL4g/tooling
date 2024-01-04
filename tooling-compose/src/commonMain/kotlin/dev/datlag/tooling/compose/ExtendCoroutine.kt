package dev.datlag.tooling.compose

import kotlinx.coroutines.*

fun mainDispatcher(): MainCoroutineDispatcher = Dispatchers.TargetMain
fun ioDispatcher(): CoroutineDispatcher = Dispatchers.TargetIO
fun defaultDispatcher(): CoroutineDispatcher = Dispatchers.TargetDefault

fun CoroutineScope.launchIO(block: suspend CoroutineScope.() -> Unit): Job {
    return this.launch(ioDispatcher()) {
        block()
    }
}

fun CoroutineScope.launchMain(block: suspend CoroutineScope.() -> Unit): Job {
    return this.launch(mainDispatcher()) {
        block()
    }
}

fun CoroutineScope.launchDefault(block: suspend CoroutineScope.() -> Unit): Job {
    return this.launch(defaultDispatcher()) {
        block()
    }
}

suspend fun <T> withIOContext(
    block: suspend CoroutineScope.() -> T
): T {
    return withContext(ioDispatcher()) {
        block()
    }
}

suspend fun <T> withMainContext(
    block: suspend CoroutineScope.() -> T
): T {
    return withContext(mainDispatcher()) {
        block()
    }
}

suspend fun <T> withDefaultContext(
    block: suspend CoroutineScope.() -> T
): T {
    return withContext(defaultDispatcher()) {
        block()
    }
}