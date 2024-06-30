package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.datlag.tooling.Platform

@Composable
expect fun PlatformButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    scale: PlatformButtonScale = PlatformButtonScale.default(),
    glow: PlatformButtonGlow = PlatformButtonGlow.default(),
    shape: PlatformButtonShape = PlatformButtonShape.default(),
    colors: PlatformButtonColors = PlatformButtonColors.default(),
    elevation: PlatformButtonElevation = PlatformButtonElevation.filled(),
    border: PlatformButtonBorder = PlatformButtonBorder.default(),
    contentPadding: PaddingValues = Platform.buttonPadding(),
    interactionSource: MutableInteractionSource? = null,
    content: @Composable RowScope.() -> Unit
)
