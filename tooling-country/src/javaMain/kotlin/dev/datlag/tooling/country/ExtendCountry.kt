package dev.datlag.tooling.country

import java.util.Locale

fun Country.Companion.fromLocale(locale: Locale): Country? {
    return parseOrNull(locale.isO3Country ?: String())
}