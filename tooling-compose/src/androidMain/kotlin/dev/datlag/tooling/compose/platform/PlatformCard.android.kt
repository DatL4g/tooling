package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Card as DefaultCard
import androidx.tv.material3.Card as TvCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.tv.material3.CardBorder
import androidx.tv.material3.CardColors
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.CardGlow
import androidx.tv.material3.CardScale
import androidx.tv.material3.CardShape
import dev.datlag.tooling.Platform
import dev.datlag.tooling.compose.animatedScale
import dev.datlag.tooling.compose.glow

@Composable
actual fun PlatformCard(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    shape: PlatformCardShape,
    colors: PlatformCardColors,
    scale: PlatformCardScale,
    border: PlatformCardBorder,
    glow: PlatformCardGlow,
    interactionSource: MutableInteractionSource?,
    content: @Composable ColumnScope.() -> Unit
) {
    val source = interactionSource ?: remember { MutableInteractionSource() }

    if (Platform.rememberIsTv()) {
        TvCard(
            onClick = {
                if (enabled) {
                    onClick()
                }
            },
            modifier = modifier,
            shape = shape.asTv(),
            colors = colors.asTv(enabled),
            scale = scale.asTv(enabled),
            border = border.asTv(enabled),
            glow = glow.asTv(),
            interactionSource = source,
            content = content
        )
    } else {
        val targetShape = shape.shape(enabled, source)

        DefaultCard(
            onClick = onClick,
            modifier = modifier
                .animatedScale(scale.scale(enabled, source), source)
                .glow(targetShape, glow.glow(source)),
            enabled = enabled,
            shape = targetShape,
            colors = colors.colors(source),
            border = border.borderStrokeOrNull(enabled, source),
            interactionSource = source,
            content = content
        )
    }
}

fun PlatformCardShape.asTv(): CardShape {
    return CardDefaults.shape(
        shape = this.shape,
        focusedShape = this.focusedShape,
        pressedShape = this.pressedShape
    )
}

@Composable
fun PlatformCardColors.asTv(enabled: Boolean): CardColors {
    return if (enabled) {
        CardDefaults.colors(
            containerColor = this.containerColor,
            contentColor = this.contentColor,
            focusedContainerColor = this.focusedContainerColor,
            focusedContentColor = this.focusedContentColor,
            pressedContainerColor = this.pressedContainerColor,
            pressedContentColor = this.pressedContentColor
        )
    } else {
        CardDefaults.colors(
            containerColor = this.disabledContainerColor,
            contentColor = this.disabledContentColor,
            focusedContainerColor = this.disabledContainerColor,
            focusedContentColor = this.disabledContentColor,
            pressedContainerColor = this.disabledContainerColor,
            pressedContentColor = this.disabledContentColor
        )
    }
}

fun PlatformCardScale.asTv(enabled: Boolean): CardScale {
    return if (enabled) {
        CardDefaults.scale(
            scale = this.scale,
            focusedScale = this.focusedScale,
            pressedScale = this.pressedScale
        )
    } else {
        CardDefaults.scale(
            scale = this.disabledScale,
            focusedScale = this.disabledScale,
            pressedScale = this.disabledScale
        )
    }
}

@Composable
fun PlatformCardBorder.asTv(enabled: Boolean): CardBorder {
    return if (enabled) {
        CardDefaults.border(
            border = this.border.asTv(),
            focusedBorder = this.focusedBorder.asTv(),
            pressedBorder = this.pressedBorder.asTv()
        )
    } else {
        CardDefaults.border(
            border = this.disabledBorder.asTv(),
            focusedBorder = this.disabledBorder.asTv(),
            pressedBorder = this.disabledBorder.asTv()
        )
    }
}

fun PlatformCardGlow.asTv(): CardGlow {
    return CardDefaults.glow(
        glow = this.glow.asTv(),
        focusedGlow = this.focusedGlow.asTv(),
        pressedGlow = this.pressedGlow.asTv()
    )
}