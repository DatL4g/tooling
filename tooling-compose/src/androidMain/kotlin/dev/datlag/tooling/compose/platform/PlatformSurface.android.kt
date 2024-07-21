package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.tv.material3.ClickableChipScale
import androidx.tv.material3.ClickableSurfaceBorder
import androidx.tv.material3.ClickableSurfaceColors
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ClickableSurfaceGlow
import androidx.tv.material3.ClickableSurfaceScale
import androidx.tv.material3.ClickableSurfaceShape
import androidx.tv.material3.SelectableSurfaceBorder
import androidx.tv.material3.SelectableSurfaceColors
import androidx.tv.material3.SelectableSurfaceDefaults
import androidx.tv.material3.SelectableSurfaceGlow
import androidx.tv.material3.SelectableSurfaceScale
import androidx.tv.material3.SelectableSurfaceShape
import androidx.tv.material3.SurfaceDefaults
import androidx.tv.material3.Surface as TvSurface
import androidx.compose.material3.Surface as DefaultSurface
import dev.datlag.tooling.Platform
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
) {
    if (Platform.rememberIsTv()) {
        TvSurface(
            modifier = modifier,
            tonalElevation = tonalElevation,
            shape = shape,
            colors = SurfaceDefaults.colors(containerColor, contentColor),
            border = border.asTv(),
            glow = glow.asTv(),
            content = { content() }
        )
    } else {
        DefaultSurface(
            modifier = modifier
                .glow(shape, glow),
            tonalElevation = tonalElevation,
            shape = shape,
            color = containerColor,
            contentColor = contentColor,
            border = border.border,
            content = content
        )
    }
}

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

    if (Platform.rememberIsTv()) {
        TvSurface(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            tonalElevation = tonalElevation,
            shape = shape.asTv(),
            colors = colors.asTv(),
            scale = scale.asTv(),
            border = border.asTv(),
            glow = glow.asTv(),
            interactionSource = source,
            content = { content() }
        )
    } else {
        val targetShape = shape.shape(enabled, source)

        DefaultSurface(
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

    if (Platform.rememberIsTv()) {
        TvSurface(
            selected = selected,
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            tonalElevation = tonalElevation,
            shape = shape.asTv(),
            colors = colors.asTv(),
            scale = scale.asTv(),
            border = border.asTv(),
            glow = glow.asTv(),
            interactionSource = source,
            content = { content() }
        )
    } else {
        val targetShape = shape.shape(enabled, selected, source)

        DefaultSurface(
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
}

@Composable
fun PlatformSurfaceShape.Clickable.asTv(): ClickableSurfaceShape {
    return ClickableSurfaceDefaults.shape(
        shape = this.shape,
        focusedShape = this.focusedShape,
        pressedShape = this.pressedShape,
        disabledShape = this.disabledShape,
        focusedDisabledShape = this.focusedDisabledShape
    )
}

@Composable
fun PlatformSurfaceColors.Clickable.asTv(): ClickableSurfaceColors {
    return ClickableSurfaceDefaults.colors(
        containerColor = this.containerColor,
        contentColor = this.contentColor,
        focusedContainerColor = this.focusedContainerColor,
        focusedContentColor = this.focusedContentColor,
        pressedContainerColor = this.pressedContainerColor,
        pressedContentColor = this.pressedContentColor,
        disabledContainerColor = this.disabledContainerColor,
        disabledContentColor = this.disabledContentColor
    )
}

fun PlatformSurfaceScale.Clickable.asTv(): ClickableSurfaceScale {
    return ClickableSurfaceDefaults.scale(
        scale = this.scale,
        focusedScale = this.focusedScale,
        pressedScale = this.pressedScale,
        disabledScale = this.disabledScale,
        focusedDisabledScale = this.focusedDisabledScale
    )
}

@Composable
fun PlatformSurfaceBorder.Clickable.asTv(): ClickableSurfaceBorder {
    return ClickableSurfaceDefaults.border(
        border = this.border.asTv(),
        focusedBorder = this.focusedBorder.asTv(),
        pressedBorder = this.pressedBorder.asTv(),
        disabledBorder = this.disabledBorder.asTv(),
        focusedDisabledBorder = this.focusedDisabledBorder.asTv()
    )
}

fun PlatformSurfaceGlow.Clickable.asTv(): ClickableSurfaceGlow {
    return ClickableSurfaceDefaults.glow(
        glow = this.glow.asTv(),
        focusedGlow = this.glow.asTv(),
        pressedGlow = this.pressedGlow.asTv()
    )
}

@Composable
fun PlatformSurfaceShape.Selectable.asTv(): SelectableSurfaceShape {
    return SelectableSurfaceDefaults.shape(
        shape = this.shape,
        focusedShape = this.focusedShape,
        pressedShape = this.pressedShape,
        selectedShape = this.selectedShape,
        disabledShape = this.disabledShape,
        focusedSelectedShape = this.focusedSelectedShape,
        focusedDisabledShape = this.focusedDisabledShape,
        pressedSelectedShape = this.pressedSelectedShape,
        selectedDisabledShape = this.selectedDisabledShape,
        focusedSelectedDisabledShape = this.focusedSelectedDisabledShape
    )
}

@Composable
fun PlatformSurfaceColors.Selectable.asTv(): SelectableSurfaceColors {
    return SelectableSurfaceDefaults.colors(
        containerColor = this.containerColor,
        contentColor = this.contentColor,
        focusedContainerColor = this.focusedContainerColor,
        focusedContentColor = this.focusedContentColor,
        pressedContainerColor = this.pressedContainerColor,
        pressedContentColor = this.pressedContentColor,
        selectedContainerColor = this.selectedContainerColor,
        selectedContentColor = this.selectedContentColor,
        disabledContainerColor = this.disabledContainerColor,
        disabledContentColor = this.disabledContentColor,
        focusedSelectedContainerColor = this.focusedSelectedContainerColor,
        focusedSelectedContentColor = this.focusedSelectedContentColor,
        pressedSelectedContainerColor = this.pressedSelectedContainerColor,
        pressedSelectedContentColor = this.pressedSelectedContentColor,
    )
}

@Composable
fun PlatformSurfaceScale.Selectable.asTv(): SelectableSurfaceScale {
    return SelectableSurfaceDefaults.scale(
        scale = this.scale,
        focusedScale = this.focusedScale,
        pressedScale = this.pressedScale,
        selectedScale = this.selectedScale,
        disabledScale = this.disabledScale,
        focusedSelectedScale = this.focusedSelectedScale,
        focusedDisabledScale = this.focusedDisabledScale,
        pressedSelectedScale = this.pressedSelectedScale,
        selectedDisabledScale = this.selectedDisabledScale,
        focusedSelectedDisabledScale = this.focusedSelectedDisabledScale
    )
}

fun PlatformSurfaceBorder.Selectable.asTv(): SelectableSurfaceBorder {
    return SelectableSurfaceDefaults.border(
        border = border.asTv(),
        focusedBorder = focusedBorder.asTv(),
        pressedBorder = pressedBorder.asTv(),
        selectedBorder = selectedBorder.asTv(),
        disabledBorder = disabledBorder.asTv(),
        focusedSelectedBorder = focusedSelectedBorder.asTv(),
        focusedDisabledBorder = focusedDisabledBorder.asTv(),
        pressedSelectedBorder = pressedSelectedBorder.asTv(),
        selectedDisabledBorder = selectedDisabledBorder.asTv(),
        focusedSelectedDisabledBorder = focusedSelectedDisabledBorder.asTv(),
    )
}

fun PlatformSurfaceGlow.Selectable.asTv(): SelectableSurfaceGlow {
    return SelectableSurfaceDefaults.glow(
        glow = glow.asTv(),
        focusedGlow = focusedGlow.asTv(),
        pressedGlow = pressedGlow.asTv(),
        selectedGlow = selectedGlow.asTv(),
        focusedSelectedGlow = focusedSelectedGlow.asTv(),
        pressedSelectedGlow = pressedSelectedGlow.asTv()
    )
}