package dev.datlag.tooling.decompose

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalStdlibApi::class)
interface ComponentModel : ComponentContext {

    /**
     * This method will be called when this [ComponentModel] is no longer used and will be destroyed.
     *
     * It is useful when the [ComponentModel] observes data and you need to clear the subscriptions to
     * prevent a memory leak, as the subscriptions might hold a reference to the [ComponentModel] even
     * after it is no longer needed.
     *
     * For specifics about the clearing sequence, refer to the [clear] method.
     */
    fun onCleared() { }

    /**
     * Clears all resources associated with this [ComponentModel] and marks it as cleared.
     *
     * A cleared [ComponentModel] should no longer be used, and any newly associated resources will be
     * immediately closed.
     *
     * **Clearing Sequence:**
     * 1. [Close][AutoCloseable.close] resources added **with** a key via [addCloseable].
     * 2. [Close][AutoCloseable.close] resources added via `constructor`.
     * 3. [Close][AutoCloseable.close] resources added **without** a key via [addCloseable].
     * 4. Invoke the [onCleared] callback.
     */
    fun clear() { }

    /**
     * Adds an [AutoCloseable] resource with an associated [key] to this [ComponentModel]. The resource
     * will be closed right **before** the [onCleared] method is called.
     *
     * If the [key] already has a resource associated with it, the old resource will be replaced
     * and closed immediately.
     *
     * If [onCleared] has already been called, the provided resource will not be added and will be
     * closed immediately.
     *
     * @param key the key to associate with the resource, for retrieval with [getCloseable].
     * @param closeable the resource to be closed when the [ComponentModel] is cleared,
     *  right **before** the [onCleared] method is called.
     */
    fun addCloseable(key: String, closeable: AutoCloseable) { }

    /**
     * Adds an [AutoCloseable] resource to this [ComponentModel]. The resource will be closed right
     * **before** the [onCleared] method is called.
     *
     * If [onCleared] has already been called, the provided resource will not be added and will be
     * closed immediately.
     *
     * @param closeable the resource to be closed when the [ComponentModel] is cleared,
     *  right **before** the [onCleared] method is called.
     */
    fun addCloseable(closeable: AutoCloseable) { }

    /**
     * Returns the [AutoCloseable] resource associated to the given [key], or `null` if such a
     * [key] is not present in this [ComponentModel].
     *
     * @param key the key associated with a resource via [addCloseable].
     */
    fun <T : AutoCloseable> getCloseable(key: String): T? = null
}

@OptIn(ExperimentalStdlibApi::class)
val ComponentModel.componentScope: CoroutineScope
    get() = synchronized(COMPONENT_SCOPE_LOCK) {
        getCloseable(COMPONENT_SCOPE_KEY) ?: createComponentScope().also { scope ->
            addCloseable(COMPONENT_SCOPE_KEY, scope)
        }
    }

private val COMPONENT_SCOPE_LOCK = SynchronizedObject()