package dev.datlag.tooling.compose

import androidx.compose.ui.Modifier

/**
 * An easy extendable [Modifier.onClick] method.
 *
 * @param [enabled] if clicking of any kind is enabled.
 * @param [onDoubleClick] what to do on double click
 * @param [onLongClick] what to do on long click
 * @param [onClick] what to do on default click
 * @return a [Modifier] with clicking option changes.
 */
expect fun Modifier.onClick(
    enabled: Boolean = true,
    onDoubleClick: (() -> Unit)? = null,
    onLongClick: (() -> Unit)? = null,
    onClick: () -> Unit,
): Modifier