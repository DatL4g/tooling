package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun PlatformCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: PlatformCardShape = PlatformCardShape.default(),
    colors: PlatformCardColors = PlatformCardColors.default(),
    scale: PlatformCardScale = PlatformCardScale.default(),
    border: PlatformCardBorder = PlatformCardBorder.default(),
    glow: PlatformCardGlow = PlatformCardGlow.default(),
    interactionSource: MutableInteractionSource? = null,
    content: @Composable ColumnScope.() -> Unit
)