package dev.datlag.tooling

import kotlin.math.max
import kotlin.math.min

/**
 * Get all numbers of a given [String].
 * This does not include floating points.
 *
 * @return a [String] of numbers or null if non where found.
 */
fun String.getDigitsOrNull(): String? {
    val replaced = this.replace("\\D+".toRegex(), "")
    return replaced.ifBlank { null }
}

fun CharSequence.safeSubSequence(from: Int, to: Int): CharSequence {
    if (this.isEmpty() || from > lastIndex) {
        return ""
    }

    val safeFrom = max(min(from, lastIndex), 0)
    return this.subSequence(
        safeFrom,
        max(safeFrom, min(to, length))
    )
}

fun String.safeSubString(from: Int, to: Int): String {
    return safeSubSequence(from, to).toString()
}