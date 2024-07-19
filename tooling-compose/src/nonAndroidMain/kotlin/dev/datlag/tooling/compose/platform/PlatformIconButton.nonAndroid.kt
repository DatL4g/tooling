package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.datlag.tooling.compose.animatedScale
import dev.datlag.tooling.compose.glow

@Composable
actual fun PlatformIconButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    scale: PlatformButtonScale,
    glow: PlatformButtonGlow,
    colors: PlatformButtonColors,
    interactionSource: MutableInteractionSource?,
    content: @Composable BoxScope.() -> Unit
) {
    val source = interactionSource ?: remember { MutableInteractionSource() }

    IconButton(
        onClick = onClick,
        modifier = modifier
            .animatedScale(scale.scale(enabled, source), source)
            .glow(CircleShape, glow.glow(source)),
        enabled = enabled,
        colors = colors.iconColors(source),
        interactionSource = source,
        content = {
            Box(
                contentAlignment = Alignment.Center
            ) {
                content()
            }
        }
    )
}