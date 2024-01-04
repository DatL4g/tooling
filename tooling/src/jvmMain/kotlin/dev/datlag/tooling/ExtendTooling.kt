package dev.datlag.tooling

import java.awt.Toolkit

/**
 * Apply [title] to the Application, this fixes appearance on some targets.
 */
fun Tooling.applicationTitle(title: String): Boolean = scopeCatching {
    val toolkit = Toolkit.getDefaultToolkit()
    val awtAppClassNameField = toolkit.javaClass.getDeclaredField("awtAppClassName")
    val working = try {
        awtAppClassNameField.isAccessible = true
        awtAppClassNameField.canAccess(null)
    } catch (ignored: Throwable) {
        awtAppClassNameField.trySetAccessible()
    }
    awtAppClassNameField.set(toolkit, title)
    working
}.getOrNull() ?: false