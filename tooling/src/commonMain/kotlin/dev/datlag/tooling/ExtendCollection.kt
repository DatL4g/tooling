package dev.datlag.tooling

import kotlin.math.max
import kotlin.math.min
import kotlin.collections.contains as defaultContains

fun <T> mutableListFrom(vararg list: Collection<T>): MutableList<T> {
    return mutableListOf<T>().apply {
        list.forEach(::addAll)
    }
}

fun <T> listFrom(vararg list: Collection<T>): List<T> = mutableListFrom(*list)

fun <T> mutableSetFrom(vararg list: Collection<T>): MutableSet<T> {
    return mutableSetOf<T>().apply {
        list.forEach(::addAll)
    }
}

fun <T> setFrom(vararg list: Collection<T>): Set<T> = mutableSetFrom(*list)

fun <T> T.asMutableList(): MutableList<T> {
    return mutableListOf(this)
}

fun <T> T.asList(): List<T> = asMutableList()

fun <T> T.asMutableSet(): MutableSet<T> {
    return mutableSetOf(this)
}

fun <T> T.asSet(): Set<T> = asMutableSet()

val <T> Collection<T>.lastIndex: Int
    get() = this.size - 1

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

fun <T> Collection<T>.safeSubSet(from: Int, to: Int): Set<T> = safeSubList(from, to).toSet()

fun Collection<String>.contains(element: String, ignoreCase: Boolean = false): Boolean {
    return if (ignoreCase) {
        this.any { it.equals(element, true) }
    } else {
        this.defaultContains(element)
    }
}