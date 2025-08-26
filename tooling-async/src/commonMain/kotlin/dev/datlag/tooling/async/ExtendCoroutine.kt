package dev.datlag.tooling.async

import kotlinx.coroutines.*

/**
 * Handy equivalent of [Dispatchers.TargetMain].
 *
 * @see [Dispatchers.TargetMain].
 * @return the [MainCoroutineDispatcher] for the main thread.
 */
fun mainDispatcher(): MainCoroutineDispatcher = Dispatchers.TargetMain

/**
 * Handy equivalent of [Dispatchers.TargetIO].
 *
 * @see [Dispatchers.TargetIO].
 * @return a [CoroutineDispatcher] for I/O operations.
 */
fun ioDispatcher(): CoroutineDispatcher = Dispatchers.TargetIO

/**
 * Handy equivalent of [Dispatchers.TargetDefault].
 *
 * @see [Dispatchers.TargetDefault].
 * @return the a [CoroutineDispatcher] for default operations.
 */
fun defaultDispatcher(): CoroutineDispatcher = Dispatchers.TargetDefault

fun virtualDispatcher(): CoroutineDispatcher? = Dispatchers.Virtual

fun virtualIODispatcher(): CoroutineDispatcher = Dispatchers.VirtualIO

/**
 * Handy equivalent of [CoroutineScope.launch] to launch directly with the [ioDispatcher].
 *
 * @see [ioDispatcher].
 * @return the [Job] which will be launched by this method.
 */
fun CoroutineScope.launchIO(block: suspend CoroutineScope.() -> Unit): Job {
    return this.launch(ioDispatcher()) {
        block()
    }
}

/**
 * Handy equivalent of [CoroutineScope.launch] to launch directly with the [mainDispatcher].
 *
 * @see [mainDispatcher].
 * @return the [Job] which will be launched by this method.
 */
fun CoroutineScope.launchMain(block: suspend CoroutineScope.() -> Unit): Job {
    return this.launch(mainDispatcher()) {
        block()
    }
}

/**
 * Handy equivalent of [CoroutineScope.launch] to launch directly with the [defaultDispatcher].
 *
 * @see [defaultDispatcher].
 * @return the [Job] which will be launched by this method.
 */
fun CoroutineScope.launchDefault(block: suspend CoroutineScope.() -> Unit): Job {
    return this.launch(defaultDispatcher()) {
        block()
    }
}

fun CoroutineScope.launchVirtual(fallback: CoroutineDispatcher, block: suspend CoroutineScope.() -> Unit): Job {
    return this.launch(virtualDispatcher() ?: fallback) {
        block()
    }
}

fun CoroutineScope.launchVirtualIO(block: suspend CoroutineScope.() -> Unit): Job {
    return this.launch(virtualIODispatcher()) {
        block()
    }
}

/**
 * Handy equivalent of [withContext] to switch directly with the [ioDispatcher].
 *
 * @see [ioDispatcher].
 * @return any object created or retrieved within this operation.
 */
suspend fun <T> withIOContext(
    block: suspend CoroutineScope.() -> T
): T {
    return withContext(ioDispatcher()) {
        block()
    }
}

/**
 * Handy equivalent of [withContext] to switch directly with the [mainDispatcher].
 *
 * @see [mainDispatcher].
 * @return any object created or retrieved within this operation.
 */
suspend fun <T> withMainContext(
    block: suspend CoroutineScope.() -> T
): T {
    return withContext(mainDispatcher()) {
        block()
    }
}

/**
 * Handy equivalent of [withContext] to switch directly with the [defaultDispatcher].
 *
 * @see [defaultDispatcher].
 * @return any object created or retrieved within this operation.
 */
suspend fun <T> withDefaultContext(
    block: suspend CoroutineScope.() -> T
): T {
    return withContext(defaultDispatcher()) {
        block()
    }
}

suspend fun <T> withVirtualContext(
    fallback: CoroutineDispatcher,
    block: suspend CoroutineScope.() -> T
): T {
    return withContext(virtualDispatcher() ?: fallback) {
        block()
    }
}

suspend fun <T> withVirtualIOContext(
    block: suspend CoroutineScope.() -> T
): T {
    return withContext(virtualIODispatcher()) {
        block()
    }
}