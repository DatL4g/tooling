package dev.datlag.tooling.decompose

import com.arkivanov.essenty.lifecycle.doOnDestroy
import kotlinx.coroutines.CoroutineScope
import kotlin.concurrent.Volatile

@OptIn(ExperimentalStdlibApi::class)
abstract class DefaultComponentModel : ComponentModel {

    private val lock = SynchronizedObject()

    /**
     * Holds a mapping between [String] keys and [AutoCloseable] resources that have been associated
     * with this [ComponentModel].
     *
     * The associated resources will be [AutoCloseable.close] right before the [ComponentModel.onCleared]
     * is called. This provides automatic resource cleanup upon [ComponentModel] release.
     *
     * For specifics about the clearing sequence, refer to the [ComponentModel.clear] method.
     *
     * **Note:** Manually [SynchronizedObject] is necessary to prevent issues on Android API 21
     * and 22. This avoids potential problems found in older versions of `ConcurrentHashMap`.
     *
     * @see <a href="https://issuetracker.google.com/37042460">b/37042460</a>
     */
    private val keyToCloseables = mutableMapOf<String, AutoCloseable>()

    /**
     * @see [keyToCloseables]
     */
    private val closeables = mutableSetOf<AutoCloseable>()

    @Volatile
    private var isCleared = false

    @Volatile
    private var clearCallback = false

    constructor()

    constructor(viewModelScope: CoroutineScope) {
        addCloseable(COMPONENT_SCOPE_KEY, viewModelScope.asCloseable())
    }

    constructor(vararg closeables: AutoCloseable) {
        this.closeables += closeables
    }

    constructor(viewModelScope: CoroutineScope, vararg closeables: AutoCloseable) {
        addCloseable(COMPONENT_SCOPE_KEY, viewModelScope.asCloseable())
        this.closeables += closeables
    }

    init {
        doOnDestroy {
            clear()
        }
    }

    final override fun clear() {
        if (isCleared) {
            if (!clearCallback) {
                onCleared()
                clearCallback = true
            }
            return
        }

        isCleared = true
        synchronized(lock) {
            for (closeable in keyToCloseables.values) {
                closeWithRuntimeException(closeable)
            }
            for (closeable in closeables) {
                closeWithRuntimeException(closeable)
            }
            // Clear only resources without keys to prevent accidental recreation of resources.
            // For example, `viewModelScope` would be recreated leading to unexpected behaviour.
            closeables.clear()
        }
        onCleared()
        clearCallback = true
    }

    final override fun addCloseable(key: String, closeable: AutoCloseable) {
        // Although no logic should be done after user calls onCleared(), we will
        // ensure that if it has already been called, the closeable attempting to
        // be added will be closed immediately to ensure there will be no leaks.
        if (isCleared) {
            closeWithRuntimeException(closeable)
            return
        }

        val oldCloseable = synchronized(lock) { keyToCloseables.put(key, closeable) }
        closeWithRuntimeException(oldCloseable)
    }

    final override fun addCloseable(closeable: AutoCloseable) {
        // Although no logic should be done after user calls onCleared(), we will
        // ensure that if it has already been called, the closeable attempting to
        // be added will be closed immediately to ensure there will be no leaks.
        if (isCleared) {
            closeWithRuntimeException(closeable)
            return
        }

        synchronized(lock) { closeables += closeable }
    }

    final override fun <T : AutoCloseable> getCloseable(key: String): T? =
        @Suppress("UNCHECKED_CAST")
        synchronized(lock) { keyToCloseables[key] as T? }

    private fun closeWithRuntimeException(closeable: AutoCloseable?) {
        try {
            closeable?.close()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}