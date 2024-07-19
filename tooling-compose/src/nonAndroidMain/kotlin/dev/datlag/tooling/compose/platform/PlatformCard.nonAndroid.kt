package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import dev.datlag.tooling.compose.animatedScale
import dev.datlag.tooling.compose.glow

@Composable
actual fun PlatformCard(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    shape: PlatformCardShape,
    colors: PlatformCardColors,
    scale: PlatformCardScale,
    border: PlatformCardBorder,
    glow: PlatformCardGlow,
    interactionSource: MutableInteractionSource?,
    content: @Composable ColumnScope.() -> Unit
) {
    val source = interactionSource ?: remember { MutableInteractionSource() }
    val targetShape = shape.shape(enabled, source)

    Card(
        onClick = onClick,
        modifier = modifier
            .animatedScale(scale.scale(enabled, source), source)
            .glow(targetShape, glow.glow(source)),
        enabled = enabled,
        shape = targetShape,
        colors = colors.colors(source),
        border = border.borderStrokeOrNull(enabled, source),
        interactionSource = source,
        content = content
    )
}