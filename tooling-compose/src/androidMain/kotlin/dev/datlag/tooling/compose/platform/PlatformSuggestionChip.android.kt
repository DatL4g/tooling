package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ClickableChipBorder
import androidx.tv.material3.ClickableChipColors
import androidx.tv.material3.ClickableChipGlow
import androidx.tv.material3.ClickableChipScale
import androidx.tv.material3.ClickableChipShape
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.SuggestionChipDefaults
import dev.datlag.tooling.Platform
import dev.datlag.tooling.compose.animatedScale
import dev.datlag.tooling.compose.glow
import androidx.compose.material3.SuggestionChip as DefaultSuggestionChip
import androidx.tv.material3.SuggestionChip as TvSuggestionChip

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
actual fun PlatformSuggestionChip(
    onClick: () -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    icon: (@Composable () -> Unit)?,
    shape: PlatformClickableChipShape,
    colors: PlatformClickableChipColors,
    scale: PlatformClickableChipScale,
    border: PlatformClickableChipBorder,
    glow: PlatformClickableChipGlow,
    interactionSource: MutableInteractionSource?,
) {
    if (Platform.rememberIsTv()) {
        TvSuggestionChip(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            shape = shape.asTvSuggestion(),
            colors = colors.asTvSuggestion(),
            scale = scale.asTvSuggestion(),
            border = border.asTvSuggestion(),
            glow = glow.asTvSuggestion(),
            interactionSource = interactionSource,
            content = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    icon?.invoke()
                    label()
                }
            }
        )
    } else {
        val source = interactionSource ?: remember { MutableInteractionSource() }
        val targetShape = shape.shape(enabled, source)

        DefaultSuggestionChip(
            onClick = onClick,
            label = label,
            modifier = modifier
                .animatedScale(scale.scale(enabled, source), source)
                .glow(targetShape, glow.glow(source)),
            enabled = enabled,
            icon = icon,
            shape = targetShape,
            colors = colors.colors(source),
            border = border.borderStrokeOrNull(enabled, source),
            interactionSource = source
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
fun PlatformClickableChipShape.asTvSuggestion(): ClickableChipShape {
    return SuggestionChipDefaults.shape(
        shape = this.shape,
        focusedShape = this.focusedShape,
        pressedShape = this.pressedShape,
        disabledShape = this.disabledShape,
        focusedDisabledShape = this.focusedDisabledShape
    )
}

@Composable
@OptIn(ExperimentalTvMaterial3Api::class)
fun PlatformClickableChipColors.asTvSuggestion(): ClickableChipColors {
    return SuggestionChipDefaults.colors(
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
fun PlatformClickableChipScale.asTvSuggestion(): ClickableChipScale {
    return SuggestionChipDefaults.scale(
        scale = this.scale,
        focusedScale = this.focusedScale,
        pressedScale = this.pressedScale,
        disabledScale = this.disabledScale,
        focusedDisabledScale = this.focusedDisabledScale
    )
}

@Composable
@OptIn(ExperimentalTvMaterial3Api::class)
fun PlatformClickableChipBorder.asTvSuggestion(): ClickableChipBorder {
    return SuggestionChipDefaults.border(
        border = this.border.asTv(),
        focusedBorder = this.focusedBorder.asTv(),
        pressedBorder = this.pressedBorder.asTv(),
        disabledBorder = this.disabledBorder.asTv(),
        focusedDisabledBorder = this.focusedDisabledBorder.asTv()
    )
}

@OptIn(ExperimentalTvMaterial3Api::class)
fun PlatformClickableChipGlow.asTvSuggestion(): ClickableChipGlow {
    return SuggestionChipDefaults.glow(
        glow = this.glow.asTv(),
        focusedGlow = this.focusedGlow.asTv(),
        pressedGlow = this.pressedGlow.asTv()
    )
}