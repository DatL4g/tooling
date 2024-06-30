package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun PlatformFilterChip(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    shape: PlatformSelectableChipShape = PlatformSelectableChipShape.filter(),
    colors: PlatformSelectableChipColors = PlatformSelectableChipColors.filter(),
    scale: PlatformSelectableChipScale = PlatformSelectableChipScale.filter(),
    border: PlatformSelectableChipBorder = PlatformSelectableChipBorder.filter(),
    glow: PlatformSelectableChipGlow = PlatformSelectableChipGlow.filter(),
    interactionSource: MutableInteractionSource? = null,
    content: @Composable () -> Unit
)