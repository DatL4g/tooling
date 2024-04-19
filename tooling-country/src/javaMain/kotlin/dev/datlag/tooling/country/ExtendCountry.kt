package dev.datlag.tooling.country

import dev.datlag.tooling.scopeCatching
import java.util.Locale

/**
 * Parse [java.util.Locale] to [Country] object.
 */
@JvmOverloads
fun Country.Companion.fromLocale(locale: Locale = Locale.getDefault()): Country? {
    return forCodeOrNull(scopeCatching { locale.isO3Country }.getOrNull() ?: "")
}