package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import dev.datlag.tooling.compose.animatedScale
import dev.datlag.tooling.compose.glow

@Composable
actual fun PlatformButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    scale: PlatformButtonScale,
    glow: PlatformButtonGlow,
    shape: PlatformButtonShape,
    colors: PlatformButtonColors,
    elevation: PlatformButtonElevation,
    border: PlatformButtonBorder,
    contentPadding: PaddingValues,
    interactionSource: MutableInteractionSource?,
    content: @Composable RowScope.() -> Unit
) {
    val source = interactionSource ?: remember { MutableInteractionSource() }
    val targetShape = shape.shape(enabled, source)

    Button(
        onClick = onClick,
        modifier = modifier
            .animatedScale(scale.scale(enabled, source), source)
            .glow(targetShape, glow.glow(source)),
        enabled = enabled,
        shape = targetShape,
        colors = colors.colors(source),
        elevation = elevation.elevation(),
        border = border.borderStrokeOrNull(enabled, source),
        contentPadding = contentPadding,
        interactionSource = source,
        content = content
    )
}