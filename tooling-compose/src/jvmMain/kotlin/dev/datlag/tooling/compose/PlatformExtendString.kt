package dev.datlag.tooling.compose

import dev.datlag.tooling.scopeCatching
import kotlinx.coroutines.CancellationException
import java.awt.Desktop
import java.net.URI

fun String.openInBrowser() = scopeCatching {
    val openBrowserCommands = arrayOf(
        arrayOf("xdg-open", "$1"),
        arrayOf("gio", "open", "$1"),
        arrayOf("gvfs-open", "$1"),
        arrayOf("gnome-open", "$1"),
        arrayOf("mate-open", "$1"),
        arrayOf("exo-open", "$1"),
        arrayOf("enlightenment_open", "$1"),
        arrayOf(
            "gdbus", "call", "--session", "--dest", "org.freedesktop.portal.Desktop",
            "--object-path", "/org/freedesktop/portal/desktop",
            "--method", "org.freedesktop.portal.OpenURI.OpenURI",
            "", "$1", "{}"
        ),
        arrayOf("open", "$1"),
        arrayOf("rundll32", "url.dll,FileProtocolHandler", "$1")
    )

    openBrowserCommands.forEach { browser ->
        try {
            val command = arrayOfNulls<String>(browser.size)
            for (i in browser.indices) {
                if (browser[i] == "$1") {
                    command[i] = this
                } else {
                    command[i] = browser[i]
                }
            }
            if (Runtime.getRuntime().exec(command).waitFor() == 0) {
                return@scopeCatching
            }
        } catch (e: Throwable) {
            if (e is CancellationException) {
                throw e
            }
        }
    }

    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
        Desktop.getDesktop().browse(URI.create(this))
    } else {
        throw IllegalStateException()
    }
}