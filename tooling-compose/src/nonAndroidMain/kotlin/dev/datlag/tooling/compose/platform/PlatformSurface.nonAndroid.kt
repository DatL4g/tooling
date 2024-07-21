package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import dev.datlag.tooling.compose.animatedScale
import dev.datlag.tooling.compose.glow

@Composable
actual fun PlatformSurface(
    modifier: Modifier,
    tonalElevation: Dp,
    shape: Shape,
    containerColor: Color,
    contentColor: Color,
    border: PlatformBorder,
    glow: PlatformGlow,
    content: @Composable () -> Unit
) = Surface(
    modifier = modifier
        .glow(shape, glow),
    tonalElevation = tonalElevation,
    shape = shape,
    color = containerColor,
    contentColor = contentColor,
    border = border.border,
    content = content
)

@Composable
actual fun PlatformSurface(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    tonalElevation: Dp,
    shape: PlatformSurfaceShape.Clickable,
    colors: PlatformSurfaceColors.Clickable,
    scale: PlatformSurfaceScale.Clickable,
    border: PlatformSurfaceBorder.Clickable,
    glow: PlatformSurfaceGlow.Clickable,
    interactionSource: MutableInteractionSource?,
    content: @Composable () -> Unit
) {
    val source = interactionSource ?: remember { MutableInteractionSource() }
    val targetShape = shape.shape(enabled, source)

    Surface(
        onClick = onClick,
        modifier = modifier
            .animatedScale(scale.scale(enabled, source), source)
            .glow(targetShape, glow.glow(source)),
        enabled = enabled,
        tonalElevation = tonalElevation,
        shape = targetShape,
        color = colors.containerColor(enabled, source),
        contentColor = colors.contentColor(enabled, source),
        border = border.borderStrokeOrNull(enabled, source),
        interactionSource = source,
        content = content
    )
}

@Composable
actual fun PlatformSurface(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    tonalElevation: Dp,
    shape: PlatformSurfaceShape.Selectable,
    colors: PlatformSurfaceColors.Selectable,
    scale: PlatformSurfaceScale.Selectable,
    border: PlatformSurfaceBorder.Selectable,
    glow: PlatformSurfaceGlow.Selectable,
    interactionSource: MutableInteractionSource?,
    content: @Composable () -> Unit
) {
    val source = interactionSource ?: remember { MutableInteractionSource() }
    val targetShape = shape.shape(enabled, selected, source)

    Surface(
        selected = selected,
        onClick = onClick,
        modifier = modifier
            .animatedScale(scale.scale(enabled, selected, source), source)
            .glow(targetShape, glow.glow(enabled, selected, source)),
        enabled = enabled,
        shape = targetShape,
        color = colors.containerColor(enabled, selected, source),
        contentColor = colors.contentColor(enabled, selected, source),
        border = border.borderStrokeOrNull(enabled, selected, source),
        interactionSource = source,
        content = content
    )
}