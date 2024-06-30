package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button as DefaultButton
import androidx.tv.material3.Button as TvButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.tv.material3.ButtonBorder
import androidx.tv.material3.ButtonColors
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.ButtonGlow
import androidx.tv.material3.ButtonScale
import androidx.tv.material3.ButtonShape
import dev.datlag.tooling.Platform
import dev.datlag.tooling.compose.animatedScale
import dev.datlag.tooling.compose.glow

@Composable
actual fun PlatformButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    scale: PlatformButtonScale,
    glow: PlatformButtonGlow,
    shape: PlatformButtonShape,
    colors: PlatformButtonColors,
    elevation: PlatformButtonElevation,
    border: PlatformButtonBorder,
    contentPadding: PaddingValues,
    interactionSource: MutableInteractionSource?,
    content: @Composable RowScope.() -> Unit
) {
    val source = interactionSource ?: remember { MutableInteractionSource() }

    if (Platform.rememberIsTv()) {
        TvButton(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            scale = scale.asTv(),
            glow = glow.asTv(),
            shape = shape.asTv(),
            colors = colors.asTv(),
            tonalElevation = elevation.elevation(enabled, source),
            border = border.asTv(),
            contentPadding = contentPadding,
            interactionSource = source,
            content = content
        )
    } else {
        val targetShape = shape.shape(enabled, source)

        DefaultButton(
            onClick = onClick,
            modifier = modifier
                .animatedScale(scale.scale(enabled, source), source)
                .glow(targetShape, glow.glow(source)),
            enabled = enabled,
            shape = targetShape,
            colors = colors.colors(source),
            elevation = elevation.elevation(),
            border = border.borderStrokeOrNull(enabled, source),
            contentPadding = contentPadding,
            interactionSource = source,
            content = content
        )
    }
}

fun PlatformButtonScale.asTv(): ButtonScale {
    return ButtonDefaults.scale(
        scale = this.scale,
        focusedScale = this.focusedScale,
        pressedScale = this.pressedScale,
        disabledScale = this.disabledScale,
        focusedDisabledScale = this.focusedDisabledScale
    )
}

fun PlatformButtonGlow.asTv(): ButtonGlow {
    return ButtonDefaults.glow(
        glow = this.glow.asTv(),
        focusedGlow = this.glow.asTv(),
        pressedGlow = this.pressedGlow.asTv()
    )
}

fun PlatformButtonShape.asTv(): ButtonShape {
    return ButtonDefaults.shape(
        shape = this.shape,
        focusedShape = this.focusedShape,
        pressedShape = this.pressedShape,
        disabledShape = this.disabledShape,
        focusedDisabledShape = this.focusedDisabledShape
    )
}

@Composable
fun PlatformButtonColors.asTv(): ButtonColors {
    return ButtonDefaults.colors(
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

@Composable
fun PlatformButtonBorder.asTv(): ButtonBorder {
    return ButtonDefaults.border(
        border = this.border.asTv(),
        focusedBorder = this.focusedBorder.asTv(),
        pressedBorder = this.pressedBorder.asTv(),
        disabledBorder = this.disabledBorder.asTv(),
        focusedDisabledBorder = this.focusedDisabledBorder.asTv()
    )
}