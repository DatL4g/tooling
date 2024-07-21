package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.datlag.tooling.Platform

@Composable
expect fun PlatformSurface(
    modifier: Modifier = Modifier,
    tonalElevation: Dp = 0.dp,
    shape: Shape = RectangleShape,
    containerColor: Color = Platform.colorScheme().surface,
    contentColor: Color = Platform.contentColorFor(containerColor),
    border: PlatformBorder = PlatformBorder.None,
    glow: PlatformGlow = PlatformGlow.None,
    content: @Composable () -> Unit
)

@Composable
expect fun PlatformSurface(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    tonalElevation: Dp = 0.dp,
    shape: PlatformSurfaceShape.Clickable = PlatformSurfaceShape.Clickable.default(),
    colors: PlatformSurfaceColors.Clickable = PlatformSurfaceColors.Clickable.default(),
    scale: PlatformSurfaceScale.Clickable = PlatformSurfaceScale.Clickable.default(),
    border: PlatformSurfaceBorder.Clickable = PlatformSurfaceBorder.Clickable.default(),
    glow: PlatformSurfaceGlow.Clickable = PlatformSurfaceGlow.Clickable.default(),
    interactionSource: MutableInteractionSource? = null,
    content: @Composable () -> Unit
)

@Composable
expect fun PlatformSurface(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    tonalElevation: Dp = 0.dp,
    shape: PlatformSurfaceShape.Selectable = PlatformSurfaceShape.Selectable.default(),
    colors: PlatformSurfaceColors.Selectable = PlatformSurfaceColors.Selectable.default(),
    scale: PlatformSurfaceScale.Selectable = PlatformSurfaceScale.Selectable.default(),
    border: PlatformSurfaceBorder.Selectable = PlatformSurfaceBorder.Selectable.default(),
    glow: PlatformSurfaceGlow.Selectable = PlatformSurfaceGlow.Selectable.default(),
    interactionSource: MutableInteractionSource? = null,
    content: @Composable () -> Unit
)