package dev.datlag.tooling.decompose.lifecycle

/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * Ported repeatOnLifecycle to Essenty Lifecycle
 */

import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.coroutines.resume

/**
 * Runs the given [block] in a new coroutine when `this` [Lifecycle] is at least at [state] and
 * suspends the execution until `this` [Lifecycle] is [Lifecycle.State.DESTROYED].
 *
 * The [block] will cancel and re-launch as the lifecycle moves in and out of the target state.
 *
 * ```
 * class MyActivity : AppCompatActivity() {
 *     override fun onCreate(savedInstanceState: Bundle?) {
 *         /* ... */
 *         // Runs the block of code in a coroutine when the lifecycle is at least STARTED.
 *         // The coroutine will be cancelled when the ON_STOP event happens and will
 *         // restart executing if the lifecycle receives the ON_START event again.
 *         lifecycleScope.launch {
 *             lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
 *                 uiStateFlow.collect { uiState ->
 *                     updateUi(uiState)
 *                 }
 *             }
 *         }
 *     }
 * }
 * ```
 *
 * The best practice is to call this function when the lifecycle is initialized. For
 * example, `onCreate` in an Activity, or `onViewCreated` in a Fragment. Otherwise, multiple
 * repeating coroutines doing the same could be created and be executed at the same time.
 *
 * Repeated invocations of `block` will run serially, that is they will always wait for the
 * previous invocation to fully finish before re-starting execution as the state moves in and out
 * of the required state.
 *
 * Warning: [Lifecycle.State.INITIALIZED] is not allowed in this API. Passing it as a
 * parameter will throw an [IllegalArgumentException].
 *
 * @param state [Lifecycle.State] in which `block` runs in a new coroutine. That coroutine
 * will cancel if the lifecycle falls below that state, and will restart if it's in that state
 * again.
 * @param block The block to run when the lifecycle is at least in [state] state.
 */
suspend fun Lifecycle.repeatOnLifecycle(
    state: Lifecycle.State = Lifecycle.State.STARTED,
    block: suspend CoroutineScope.() -> Unit
) {
    require(state !== Lifecycle.State.INITIALIZED) {
        "repeatOnLifecycle cannot start work with the INITIALIZED lifecycle state."
    }
    if (this@repeatOnLifecycle.state === Lifecycle.State.DESTROYED) {
        return
    }
    // This scope is required to preserve context before we move to Dispatchers.Main
    coroutineScope {
        withContext(Dispatchers.Main.immediate) {
            // Check the current state of the lifecycle as the previous check is not guaranteed
            // to be done on the main thread.
            if (this@repeatOnLifecycle.state === Lifecycle.State.DESTROYED) return@withContext
            // Instance of the running repeating coroutine
            var launchedJob: Job? = null
            // Registered observer
            var observer: Lifecycle.Callbacks? = null
            try {
                // Suspend the coroutine until the lifecycle is destroyed or
                // the coroutine is cancelled
                suspendCancellableCoroutine { cont ->
                    // Lifecycle observers that executes `block` when the lifecycle reaches certain state, and
                    // cancels when it falls below that state.
                    val mutex = Mutex()
                    observer = object : Lifecycle.Callbacks {

                        override fun onCreate() {
                            if (state == Lifecycle.State.CREATED) {
                                startWork()
                            }
                        }

                        override fun onStart() {
                            if (state == Lifecycle.State.STARTED) {
                                startWork()
                            }
                        }

                        override fun onResume() {
                            if (state == Lifecycle.State.RESUMED) {
                                startWork()
                            }
                        }

                        override fun onPause() {
                            if (state == Lifecycle.State.RESUMED) {
                                cancelWork()
                            }
                        }

                        override fun onStop() {
                            if (state == Lifecycle.State.STARTED) {
                                cancelWork()
                            }
                        }

                        override fun onDestroy() {
                            if (state == Lifecycle.State.CREATED) {
                                cancelWork()
                            }
                            cont.resume(Unit)
                        }

                        private fun startWork() {
                            // Launch the repeating work preserving the calling context
                            launchedJob = this@coroutineScope.launch {
                                // Mutex makes invocations run serially,
                                // coroutineScope ensures all child coroutines finish
                                mutex.withLock {
                                    coroutineScope {
                                        block()
                                    }
                                }
                            }
                        }

                        private fun cancelWork() {
                            launchedJob?.cancel()
                            launchedJob = null
                        }
                    }.also {
                        this@repeatOnLifecycle.subscribe(it)
                    }
                }
            } finally {
                launchedJob?.cancel()
                observer?.let {
                    this@repeatOnLifecycle.unsubscribe(it)
                }
            }
        }
    }
}

/**
 * [LifecycleOwner]'s extension function for [Lifecycle.repeatOnLifecycle] to allow an easier
 * call to the API from LifecycleOwners such as Activities and Fragments.
 *
 * ```
 * class MyActivity : AppCompatActivity() {
 *     override fun onCreate(savedInstanceState: Bundle?) {
 *         /* ... */
 *         // Runs the block of code in a coroutine when the lifecycle is at least STARTED.
 *         // The coroutine will be cancelled when the ON_STOP event happens and will
 *         // restart executing if the lifecycle receives the ON_START event again.
 *         lifecycleScope.launch {
 *             repeatOnLifecycle(Lifecycle.State.STARTED) {
 *                 uiStateFlow.collect { uiState ->
 *                     updateUi(uiState)
 *                 }
 *             }
 *         }
 *     }
 * }
 * ```
 *
 * @see Lifecycle.repeatOnLifecycle
 */
suspend fun LifecycleOwner.repeatOnLifecycle(
    state: Lifecycle.State = Lifecycle.State.STARTED,
    block: suspend CoroutineScope.() -> Unit
): Unit = lifecycle.repeatOnLifecycle(state, block)