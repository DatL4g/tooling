package dev.datlag.tooling.country

import java.util.Locale

/**
 * Parse [java.util.Locale] to [Country] object.
 */
@JvmOverloads
fun Country.Companion.fromLocale(locale: Locale = Locale.getDefault()): Country? {
    return forCodeOrNull(locale.isO3Country ?: String())
}