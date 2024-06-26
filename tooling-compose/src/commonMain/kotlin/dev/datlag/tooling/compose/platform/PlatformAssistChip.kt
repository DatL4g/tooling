package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun PlatformAssistChip(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    shape: PlatformClickableChipShape = PlatformClickableChipShape.assist(),
    colors: PlatformClickableChipColors = PlatformClickableChipColors.assist(),
    scale: PlatformClickableChipScale = PlatformClickableChipScale.assist(),
    border: PlatformClickableChipBorder = PlatformClickableChipBorder.assist(),
    glow: PlatformClickableChipGlow = PlatformClickableChipGlow.assist(),
    interactionSource: MutableInteractionSource? = null,
    content: @Composable () -> Unit
)