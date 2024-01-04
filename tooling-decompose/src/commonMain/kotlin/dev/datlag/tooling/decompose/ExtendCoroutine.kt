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

fun CoroutineScope(context: CoroutineContext, lifecycle: Lifecycle): CoroutineScope {
    val scope = CoroutineScope(context)
    lifecycle.doOnDestroy(scope::cancel)
    return scope
}

fun LifecycleOwner.coroutineScope(context: CoroutineContext): CoroutineScope = CoroutineScope(context, lifecycle)

fun LifecycleOwner.ioScope() = CoroutineScope(ioDispatcher() + SupervisorJob(), lifecycle)
fun LifecycleOwner.mainScope() = CoroutineScope(mainDispatcher() + SupervisorJob(), lifecycle)
fun LifecycleOwner.defaultScope() = CoroutineScope(defaultDispatcher() + SupervisorJob(), lifecycle)

fun LifecycleOwner.launchIO(block: suspend  CoroutineScope.() -> Unit): Job {
    return ioScope().launchIO(block)
}

fun LifecycleOwner.launchMain(block: suspend  CoroutineScope.() -> Unit): Job {
    return mainScope().launchMain(block)
}

fun LifecycleOwner.launchDefault(block: suspend  CoroutineScope.() -> Unit): Job {
    return defaultScope().launchDefault(block)
}