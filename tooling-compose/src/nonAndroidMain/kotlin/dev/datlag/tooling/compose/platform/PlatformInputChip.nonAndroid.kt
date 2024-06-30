package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.InputChip
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import dev.datlag.tooling.compose.animatedScale
import dev.datlag.tooling.compose.glow

@Composable
actual fun PlatformInputChip(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    leadingIcon: @Composable (() -> Unit)?,
    avatar: @Composable (() -> Unit)?,
    trailingIcon: @Composable (() -> Unit)?,
    shape: PlatformSelectableChipShape,
    colors: PlatformSelectableChipColors,
    scale: PlatformSelectableChipScale,
    border: PlatformSelectableChipBorder,
    glow: PlatformSelectableChipGlow,
    interactionSource: MutableInteractionSource?,
    content: @Composable () -> Unit
) {
    val source = interactionSource ?: remember { MutableInteractionSource() }
    val targetShape = shape.shape(enabled, selected, source)

    InputChip(
        selected = selected,
        onClick = onClick,
        label = content,
        modifier = modifier
            .animatedScale(scale.scale(enabled, selected, source), source)
            .glow(targetShape, glow.glow(enabled, selected, source)),
        enabled = enabled,
        leadingIcon = leadingIcon,
        avatar = avatar,
        trailingIcon = trailingIcon,
        shape = targetShape,
        colors = colors.colors(source),
        border = border.borderStrokeOrNull(enabled, selected, source),
        interactionSource = source
    )
}