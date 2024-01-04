package dev.datlag.tooling.compose

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher

expect val Dispatchers.TargetMain: MainCoroutineDispatcher
expect val Dispatchers.TargetIO: CoroutineDispatcher
expect val Dispatchers.TargetDefault: CoroutineDispatcher