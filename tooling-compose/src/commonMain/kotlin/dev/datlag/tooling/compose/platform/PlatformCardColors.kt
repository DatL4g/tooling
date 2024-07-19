package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material3.CardColors
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.dp
import dev.datlag.tooling.Platform

@Immutable
class PlatformCardColors(
    val containerColor: Color,
    val contentColor: Color,
    val focusedContainerColor: Color,
    val focusedContentColor: Color,
    val pressedContainerColor: Color,
    val pressedContentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color
) {

    fun containerColor(
        enabled: Boolean,
        focused: Boolean,
        pressed: Boolean
    ): Color {
        return when {
            pressed && enabled -> pressedContainerColor
            focused && enabled -> focusedContainerColor
            enabled -> containerColor
            else -> disabledContainerColor
        }
    }

    fun contentColor(
        enabled: Boolean,
        focused: Boolean,
        pressed: Boolean,
    ): Color {
        return when {
            pressed && enabled -> pressedContentColor
            focused && enabled -> focusedContentColor
            enabled -> contentColor
            else -> disabledContentColor
        }
    }

    fun colors(
        focused: Boolean,
        pressed: Boolean
    ): CardColors {
        val enabledContainerColor = containerColor(
            enabled = true,
            focused = focused,
            pressed = pressed
        )
        val enabledContentColor = contentColor(
            enabled = true,
            focused = focused,
            pressed = pressed
        )

        val disabledContainerColor = containerColor(
            enabled = false,
            focused = focused,
            pressed = pressed
        )
        val disabledContentColor = contentColor(
            enabled = false,
            focused = focused,
            pressed = pressed
        )

        return CardColors(
            containerColor = enabledContainerColor,
            contentColor = enabledContentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor
        )
    }

    @Composable
    fun colors(
        interactionSource: MutableInteractionSource
    ): CardColors {
        val focused by interactionSource.collectIsFocusedAsState()
        val pressed by interactionSource.collectIsPressedAsState()

        return colors(
            focused = focused,
            pressed = pressed
        )
    }

    companion object {
        @Composable
        fun default(
            containerColor: Color = Platform.colorScheme().surfaceVariant,
            contentColor: Color = Platform.contentColorFor(containerColor),
            focusedContainerColor: Color = containerColor,
            focusedContentColor: Color = Platform.contentColorFor(focusedContainerColor),
            pressedContainerColor: Color = focusedContainerColor,
            pressedContentColor: Color = Platform.contentColorFor(pressedContainerColor),
            disabledContainerColor: Color = Platform.colorScheme().surfaceVariant.copy(
                alpha = 0.38f
            ).compositeOver(
                Platform.colorScheme().surfaceColorAtElevation(0.dp)
            ),
            disabledContentColor: Color = Platform.contentColorFor(Platform.colorScheme().surfaceVariant).copy(alpha = 0.38f)
        ) = PlatformCardColors(
            containerColor = containerColor,
            contentColor = contentColor,
            focusedContainerColor = focusedContainerColor,
            focusedContentColor = focusedContentColor,
            pressedContainerColor = pressedContainerColor,
            pressedContentColor = pressedContentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor
        )
    }
}