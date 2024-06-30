package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun PlatformInputChip(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: (@Composable () -> Unit)? = null,
    avatar: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    shape: PlatformSelectableChipShape = PlatformSelectableChipShape.input(hasAvatar = avatar != null),
    colors: PlatformSelectableChipColors = PlatformSelectableChipColors.input(),
    scale: PlatformSelectableChipScale = PlatformSelectableChipScale.input(),
    border: PlatformSelectableChipBorder = PlatformSelectableChipBorder.input(hasAvatar = avatar != null),
    glow: PlatformSelectableChipGlow = PlatformSelectableChipGlow.input(),
    interactionSource: MutableInteractionSource? = null,
    content: @Composable () -> Unit
)