package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun PlatformSuggestionChip(
    onClick: () -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: (@Composable () -> Unit)? = null,
    shape: PlatformClickableChipShape = PlatformClickableChipShape.suggestion(),
    colors: PlatformClickableChipColors = PlatformClickableChipColors.suggestion(),
    scale: PlatformClickableChipScale = PlatformClickableChipScale.suggestion(),
    border: PlatformClickableChipBorder = PlatformClickableChipBorder.suggestion(),
    glow: PlatformClickableChipGlow = PlatformClickableChipGlow.suggestion(),
    interactionSource: MutableInteractionSource? = null
)
