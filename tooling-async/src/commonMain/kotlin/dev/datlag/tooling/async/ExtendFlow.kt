package dev.datlag.tooling.async

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull

/**
 * Collect [Flow] without throwing an exception, except [CancellationException] to cancel parent jobs.
 * Tries to emit a single value if [Flow.collect] can not be called.
 *
 * @param [collector] the [FlowCollector] where you want to receive values.
 */
suspend fun <T> Flow<T>.collectSafe(collector: FlowCollector<T>) {
    suspendCatching {
        this@collectSafe.collect(collector)
    }.getOrNull() ?: suspendCatching {
        this@collectSafe.firstOrNull()
    }.getOrNull()?.let { collector.emit(it) }
}

/**
 * Collect [StateFlow] without throwing an exception, except [CancellationException] to cancel parent jobs.
 * Tries to emit a single value if [StateFlow.collect] can not be called.
 *
 * @param [collector] the [FlowCollector] where you want to receive values.
 */
suspend fun <T> StateFlow<T>.collectSafe(collector: FlowCollector<T>) {
    suspendCatching {
        this@collectSafe.collect(collector)
    }.getOrNull() ?: collector.emit(this.value)
}