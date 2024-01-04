package dev.datlag.tooling

fun systemProperty(key: String): String? = scopeCatching {
    System.getProperty(key).ifBlank { null }
}.getOrNull()

fun systemProperty(key: String, value: String): String? = scopeCatching {
    System.setProperty(key, value).ifBlank { null }
}.getOrNull()

fun systemEnv(key: String): String? = scopeCatching {
    System.getenv(key).ifBlank { null }
}.getOrNull()