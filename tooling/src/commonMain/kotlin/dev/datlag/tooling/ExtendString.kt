package dev.datlag.tooling

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