package dev.datlag.tooling

import kotlin.coroutines.cancellation.CancellationException

fun <T> scopeCatching(block: () -> T): Result<T> = try {
    Result.success(block())
} catch (e: Throwable) {
    if (e is CancellationException) {
        throw e
    }
    Result.failure(e)
}

fun <T> safeCast(block: () -> T?): T? {
    return scopeCatching {
        block()
    }.getOrNull()
}

inline fun <reified T> Any?.safeCast(): T? {
    return safeCast {
        if (this is T) {
            this
        } else {
            this as? T?
        }
    }
}