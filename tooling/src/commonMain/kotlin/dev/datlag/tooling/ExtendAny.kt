package dev.datlag.tooling

import kotlin.coroutines.cancellation.CancellationException

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
 * Cast any object within without throwing any exception, except [CancellationException] to cancel parent jobs.
 *
 * @return the wanted type or null if exception was thrown.
 */
fun <T> safeCast(block: () -> T?): T? {
    return scopeCatching {
        block()
    }.getOrNull()
}

/**
 * Cast any object to the wanted type without throwing any exception, except [CancellationException] to cancel parent jobs.
 *
 * @return the wanted type or null if casting threw an exception.
 */
inline fun <reified T> Any?.safeCast(): T? {
    return safeCast {
        if (this is T) {
            this
        } else {
            this as? T?
        }
    }
}