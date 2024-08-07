package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.SuggestionChip
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import dev.datlag.tooling.compose.animatedScale
import dev.datlag.tooling.compose.glow

@Composable
actual fun PlatformSuggestionChip(
    onClick: () -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    icon: (@Composable () -> Unit)?,
    shape: PlatformClickableChipShape,
    colors: PlatformClickableChipColors,
    scale: PlatformClickableChipScale,
    border: PlatformClickableChipBorder,
    glow: PlatformClickableChipGlow,
    interactionSource: MutableInteractionSource?,
) {
    val source = interactionSource ?: remember { MutableInteractionSource() }
    val targetShape = shape.shape(enabled, source)

    SuggestionChip(
        onClick = onClick,
        label = label,
        modifier = modifier
            .animatedScale(scale.scale(enabled, source), source)
            .glow(targetShape, glow.glow(source)),
        enabled = enabled,
        icon = icon,
        shape = targetShape,
        colors = colors.colors(source),
        border = border.borderStrokeOrNull(enabled, source),
        interactionSource = source
    )
}