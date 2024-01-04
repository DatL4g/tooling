package dev.datlag.tooling.async

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull

suspend fun <T> Flow<T>.collectSafe(collector: FlowCollector<T>) {
    suspendCatching {
        this@collectSafe.collect(collector)
    }.getOrNull() ?: suspendCatching {
        this@collectSafe.firstOrNull()
    }.getOrNull()?.let { collector.emit(it) }
}

suspend fun <T> StateFlow<T>.collectSafe(collector: FlowCollector<T>) {
    suspendCatching {
        this@collectSafe.collect(collector)
    }.getOrNull() ?: collector.emit(this.value)
}