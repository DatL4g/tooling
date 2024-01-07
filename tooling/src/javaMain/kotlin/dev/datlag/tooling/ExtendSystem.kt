package dev.datlag.tooling

import kotlin.coroutines.cancellation.CancellationException

/**
 * Run [System.getProperty] without throwing an exception, except [CancellationException] to cancel parent jobs.
 *
 * @param [key] the system property key you want to get.
 * @return a [String] or null if the call failed or was empty.
 */
fun systemProperty(key: String): String? = scopeCatching {
    System.getProperty(key).ifBlank { null }
}.getOrNull()

/**
 * Run [System.setProperty] without throwing an exception, except [CancellationException] to cancel parent jobs.
 *
 * @param [key] the system property key you want to set.
 * @param [value] the value you want to set for the system property [key].
 * @return a [String] or null if the call failed or was empty.
 */
fun systemProperty(key: String, value: String): String? = scopeCatching {
    System.setProperty(key, value).ifBlank { null }
}.getOrNull()

/**
 * Run [System.getenv] without throwing an exception, except [CancellationException] to cancel parent jobs.
 *
 * @param [key] the system environment variable key you want to get.
 * @return a [String] or null if the call failed or was empty.
 */
fun systemEnv(key: String): String? = scopeCatching {
    System.getenv(key).ifBlank { null }
}.getOrNull()