package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
expect fun PlatformSuggestionChip(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: PlatformClickableChipShape = PlatformClickableChipShape.suggestion(),
    colors: PlatformClickableChipColors = PlatformClickableChipColors.suggestion(),
    scale: PlatformClickableChipScale = PlatformClickableChipScale.suggestion(),
    border: PlatformClickableChipBorder = PlatformClickableChipBorder.suggestion(),
    glow: PlatformClickableChipGlow = PlatformClickableChipGlow.suggestion(),
    interactionSource: MutableInteractionSource? = null,
    content: @Composable () -> Unit
)

@Composable
fun PlatformSuggestionChip(
    onClick: () -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: @Composable () -> Unit,
    shape: PlatformClickableChipShape = PlatformClickableChipShape.suggestion(),
    colors: PlatformClickableChipColors = PlatformClickableChipColors.suggestion(),
    scale: PlatformClickableChipScale = PlatformClickableChipScale.suggestion(),
    border: PlatformClickableChipBorder = PlatformClickableChipBorder.suggestion(),
    glow: PlatformClickableChipGlow = PlatformClickableChipGlow.suggestion(),
    interactionSource: MutableInteractionSource? = null,
) = PlatformSuggestionChip(
    onClick = onClick,
    modifier = modifier,
    enabled = enabled,
    shape = shape,
    colors = colors,
    scale = scale,
    border = border,
    glow = glow,
    interactionSource = interactionSource,
    content = {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon()
            label()
        }
    }
)