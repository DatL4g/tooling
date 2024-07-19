package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun PlatformIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    scale: PlatformButtonScale = PlatformButtonScale.icon(),
    glow: PlatformButtonGlow = PlatformButtonGlow.icon(),
    colors: PlatformButtonColors = PlatformButtonColors.icon(),
    interactionSource: MutableInteractionSource? = null,
    content: @Composable BoxScope.() -> Unit
)