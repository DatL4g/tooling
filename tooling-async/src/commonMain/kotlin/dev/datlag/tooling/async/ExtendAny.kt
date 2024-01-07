package dev.datlag.tooling.async

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import dev.datlag.tooling.safeCast

/**
 * Equivalent of [runCatching] but able to throw [CancellationException] to cancel parent jobs.
 */
fun <T> scopeCatching(block: () -> T): Result<T> = try {
    Result.success(block())
} catch (e: Throwable) {
    if (e is CancellationException) {
        throw e
    }
    Result.failure(e)
}

/**
 * Equivalent of a suspendable [runCatching] but able to throw [CancellationException] to cancel parent jobs.
 */
suspend fun <T> suspendCatching(block: suspend CoroutineScope.() -> T): Result<T> = coroutineScope {
    try {
        Result.success(block(this))
    } catch (e: Throwable) {
        if (e is CancellationException) {
            throw e
        }
        Result.failure(e)
    }
}

/**
 * Equivalent of a suspendable [safeCast], able to throw [CancellationException] to cancel parent jobs.
 */
suspend fun <T> suspendSafeCast(block: suspend CoroutineScope.() -> T?): T? {
    return suspendCatching {
        block()
    }.getOrNull()
}