package dev.datlag.tooling

import kotlin.math.max
import kotlin.math.min
import kotlin.collections.contains as defaultContains

/**
 * Combine any [Iterable] of the same type to one [MutableList].
 *
 * @return a [MutableList] of all provided items in iterables.
 */
fun <T> mutableListFrom(vararg list: Iterable<T>): MutableList<T> {
    return mutableListOf<T>().apply {
        list.forEach(::addAll)
    }
}

/**
 * Combine any [Iterable] of the same type to one [List].
 *
 * @return a [List] of all provided items in iterables.
 */
fun <T> listFrom(vararg list: Iterable<T>): List<T> = mutableListFrom(*list)

/**
 * Combine any [Iterable] of the same type to one [MutableSet].
 *
 * @return a [MutableSet] of all provided items in iterables.
 */
fun <T> mutableSetFrom(vararg list: Iterable<T>): MutableSet<T> {
    return mutableSetOf<T>().apply {
        list.forEach(::addAll)
    }
}

/**
 * Combine any [Iterable] of the same type to one [Set].
 *
 * @return a [Set] of all provided items in iterables.
 */
fun <T> setFrom(vararg list: Iterable<T>): Set<T> = mutableSetFrom(*list)

/**
 * Provides the last existing index of any [Collection]
 */
val Collection<*>.lastIndex: Int
    get() = this.size - 1

/**
 * Run [List.subList] on any [Collection] safely without throwing any exception if the provided start/end values do not exist.
 *
 * @return a [List] which only holds elements of the original [Collection] within it's provided bounds.
 */
fun <T> Collection<T>.safeSubList(from: Int, to: Int): List<T> {
    if (this.isEmpty()) {
        return emptyList()
    }

    val safeFrom = max(min(from, lastIndex), 0)
    return this.toList().subList(
        safeFrom,
        max(safeFrom, min(to, size))
    )
}

/**
 * Run [List.subList] on any [Collection] safely without throwing any exception if the provided start/end values do not exist.
 *
 * @return a [Set] which only holds elements of the original [Collection] within it's provided bounds.
 */
fun <T> Collection<T>.safeSubSet(from: Int, to: Int): Set<T> = toSet().safeSubList(from, to).toSet()

/**
 * Check if a [String]-[Iterable] contains a specific element with the option to ignore case-sensitivity.
 *
 * @return whether the [Iterable] contains such an element or not
 */
fun Iterable<String>.contains(element: String, ignoreCase: Boolean = false): Boolean {
    return if (ignoreCase) {
        this.any { it.equals(element, true) }
    } else {
        this.defaultContains(element)
    }
}