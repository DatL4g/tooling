package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.tv.material3.AssistChipDefaults as TvChipDefaults
import androidx.tv.material3.Border
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Glow
import androidx.compose.material3.AssistChip as DefaultAssistChip
import androidx.tv.material3.ClickableChipColors as TvChipColors
import androidx.tv.material3.AssistChip as TvAssistChip
import androidx.tv.material3.ClickableChipShape as TvChipShape
import androidx.tv.material3.ClickableChipScale as TvChipScale
import androidx.tv.material3.ClickableChipBorder as TvChipBorder
import androidx.tv.material3.ClickableChipGlow as TvChipGlow
import androidx.tv.material3.Border as TvBorder
import androidx.tv.material3.Glow as TvGlow
import dev.datlag.tooling.Platform
import dev.datlag.tooling.compose.animatedScale
import dev.datlag.tooling.compose.glow

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
actual fun PlatformAssistChip(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    leadingIcon: @Composable (() -> Unit)?,
    trailingIcon: @Composable (() -> Unit)?,
    shape: PlatformClickableChipShape,
    colors: PlatformClickableChipColors,
    scale: PlatformClickableChipScale,
    border: PlatformClickableChipBorder,
    glow: PlatformClickableChipGlow,
    interactionSource: MutableInteractionSource?,
    content: @Composable () -> Unit
) {
    if (Platform.rememberIsTv()) {
        TvAssistChip(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            shape = shape.asTvAssist(),
            colors = colors.asTvAssist(),
            scale = scale.asTvAssist(),
            border = border.asTvAssist(),
            glow = glow.asTvAssist(),
            interactionSource = interactionSource,
            content = content
        )
    } else {
        val source = interactionSource ?: remember { MutableInteractionSource() }
        val targetShape = shape.shape(enabled, source)

        DefaultAssistChip(
            onClick = onClick,
            label = content,
            modifier = modifier
                .animatedScale(scale.scale(enabled, source), source)
                .glow(targetShape, glow.glow(source)),
            enabled = enabled,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            shape = targetShape,
            colors = colors.colors(source),
            border = border.borderStrokeOrNull(enabled, source),
            interactionSource = source
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
fun PlatformClickableChipShape.asTvAssist(): TvChipShape {
    return TvChipDefaults.shape(
        shape = this.shape,
        focusedShape = this.focusedShape,
        pressedShape = this.pressedShape,
        disabledShape = this.disabledShape,
        focusedDisabledShape = this.focusedDisabledShape
    )
}

@Composable
@OptIn(ExperimentalTvMaterial3Api::class)
fun PlatformClickableChipColors.asTvAssist(): TvChipColors {
    return TvChipDefaults.colors(
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

@OptIn(ExperimentalTvMaterial3Api::class)
fun PlatformClickableChipScale.asTvAssist(): TvChipScale {
    return TvChipDefaults.scale(
        scale = this.scale,
        focusedScale = this.focusedScale,
        pressedScale = this.pressedScale,
        disabledScale = this.disabledScale,
        focusedDisabledScale = this.focusedDisabledScale
    )
}

fun PlatformBorder.asTv(): TvBorder {
    return Border(
        border = this.border,
        inset = this.inset,
        shape = this.shape
    )
}

@Composable
@OptIn(ExperimentalTvMaterial3Api::class)
fun PlatformClickableChipBorder.asTvAssist(): TvChipBorder {
    return TvChipDefaults.border(
        border = this.border.asTv(),
        focusedBorder = this.focusedBorder.asTv(),
        pressedBorder = this.pressedBorder.asTv(),
        disabledBorder = this.disabledBorder.asTv(),
        focusedDisabledBorder = this.focusedDisabledBorder.asTv()
    )
}

fun PlatformGlow.asTv(): TvGlow {
    return Glow(
        elevationColor = this.elevationColor,
        elevation = this.elevation
    )
}

@OptIn(ExperimentalTvMaterial3Api::class)
fun PlatformClickableChipGlow.asTvAssist(): TvChipGlow {
    return TvChipDefaults.glow(
        glow = this.glow.asTv(),
        focusedGlow = this.focusedGlow.asTv(),
        pressedGlow = this.pressedGlow.asTv()
    )
}
