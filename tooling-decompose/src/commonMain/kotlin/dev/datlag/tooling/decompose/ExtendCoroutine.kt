package dev.datlag.tooling.decompose

import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.lifecycle.doOnDestroy
import dev.datlag.tooling.compose.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

/**
 * Create a [CoroutineScope] in Decompose easily.
 *
 * @param [context] the context used for the [CoroutineScope].
 * @param [lifecycle] the lifecycle the [CoroutineScope] is bound to.
 * @return a lifecycle-aware [CoroutineScope]
 */
fun CoroutineScope(context: CoroutineContext, lifecycle: Lifecycle): CoroutineScope {
    val scope = CoroutineScope(context)
    lifecycle.doOnDestroy(scope::cancel)
    return scope
}

/**
 * Extension function to create a [CoroutineScope] on the current [Lifecycle].
 *
 * @param [context] the context used for the [CoroutineScope].
 * @return a lifecycle-aware [CoroutineScope]
 */
fun LifecycleOwner.coroutineScope(context: CoroutineContext): CoroutineScope = CoroutineScope(context, lifecycle)

/**
 * Extension function to create a [CoroutineScope] on the current [Lifecycle] using the platform [ioDispatcher].
 *
 * @see [ioDispatcher]
 * @return a lifecycle-aware [CoroutineScope] on [ioDispatcher]
 */
fun LifecycleOwner.ioScope() = CoroutineScope(ioDispatcher() + SupervisorJob(), lifecycle)

/**
 * Extension function to create a [CoroutineScope] on the current [Lifecycle] using the platform [mainDispatcher].
 *
 * @see [mainDispatcher]
 * @return a lifecycle-aware [CoroutineScope] on [mainDispatcher]
 */
fun LifecycleOwner.mainScope() = CoroutineScope(mainDispatcher() + SupervisorJob(), lifecycle)

/**
 * Extension function to create a [CoroutineScope] on the current [Lifecycle] using the platform [defaultDispatcher].
 *
 * @see [defaultDispatcher]
 * @return a lifecycle-aware [CoroutineScope] on [defaultDispatcher]
 */
fun LifecycleOwner.defaultScope() = CoroutineScope(defaultDispatcher() + SupervisorJob(), lifecycle)

/**
 * Extension function to launch a [CoroutineScope] on the current [Lifecycle] using the platform [ioDispatcher].
 *
 * @see [ioDispatcher]
 * @return a [Job] on a lifecycle-aware [CoroutineScope] on [ioDispatcher]
 */
fun LifecycleOwner.launchIO(block: suspend  CoroutineScope.() -> Unit): Job {
    return ioScope().launchIO(block)
}

/**
 * Extension function to launch a [CoroutineScope] on the current [Lifecycle] using the platform [mainDispatcher].
 *
 * @see [mainDispatcher]
 * @return a [Job] on a lifecycle-aware [CoroutineScope] on [mainDispatcher]
 */
fun LifecycleOwner.launchMain(block: suspend  CoroutineScope.() -> Unit): Job {
    return mainScope().launchMain(block)
}

/**
 * Extension function to launch a [CoroutineScope] on the current [Lifecycle] using the platform [defaultDispatcher].
 *
 * @see [defaultDispatcher]
 * @return a [Job] on a lifecycle-aware [CoroutineScope] on [defaultDispatcher]
 */
fun LifecycleOwner.launchDefault(block: suspend  CoroutineScope.() -> Unit): Job {
    return defaultScope().launchDefault(block)
}