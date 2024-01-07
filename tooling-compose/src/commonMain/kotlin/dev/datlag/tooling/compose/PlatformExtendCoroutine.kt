package dev.datlag.tooling.compose

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher

/**
 * The main dispatcher on the current platform.
 */
expect val Dispatchers.TargetMain: MainCoroutineDispatcher

/**
 * The IO dispatcher on the current platform.
 */
expect val Dispatchers.TargetIO: CoroutineDispatcher

/**
 * The default dispatcher on the current platform.
 */
expect val Dispatchers.TargetDefault: CoroutineDispatcher