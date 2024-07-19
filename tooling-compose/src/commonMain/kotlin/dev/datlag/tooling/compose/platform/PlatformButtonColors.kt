package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ChipColors
import androidx.compose.material3.IconButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import dev.datlag.tooling.Platform

@Immutable
class PlatformButtonColors(
    val containerColor: Color,
    val contentColor: Color,
    val focusedContainerColor: Color,
    val focusedContentColor: Color,
    val pressedContainerColor: Color,
    val pressedContentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color,
) {

    fun containerColor(
        enabled: Boolean,
        focused: Boolean,
        pressed: Boolean,
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
    ): ButtonColors {
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

        return ButtonColors(
            containerColor = enabledContainerColor,
            contentColor = enabledContentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor
        )
    }

    fun iconColors(
        focused: Boolean,
        pressed: Boolean
    ): IconButtonColors {
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

        return IconButtonColors(
            containerColor = enabledContainerColor,
            contentColor = enabledContentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor
        )
    }

    @Composable
    fun colors(
        interactionSource: MutableInteractionSource
    ): ButtonColors {
        val focused by interactionSource.collectIsFocusedAsState()
        val pressed by interactionSource.collectIsPressedAsState()

        return colors(
            focused = focused,
            pressed = pressed
        )
    }

    @Composable
    fun iconColors(
        interactionSource: MutableInteractionSource
    ): IconButtonColors {
        val focused by interactionSource.collectIsFocusedAsState()
        val pressed by interactionSource.collectIsPressedAsState()

        return iconColors(
            focused = focused,
            pressed = pressed
        )
    }

    companion object {
        @Composable
        fun default(
            containerColor: Color = if (Platform.rememberIsTv()) {
                Platform.colorScheme().surfaceVariant.copy(alpha = 0.8f)
            } else {
                Platform.colorScheme().primary
            },
            contentColor: Color = if (Platform.rememberIsTv()) {
                Platform.colorScheme().onSurface.copy(alpha = 0.8f)
            } else {
                Platform.colorScheme().onPrimary
            },
            focusedContainerColor: Color = if (Platform.rememberIsTv()) {
                Platform.colorScheme().onSurface
            } else {
                containerColor
            },
            focusedContentColor: Color = if (Platform.rememberIsTv()) {
                Platform.colorScheme().inverseOnSurface
            } else {
                contentColor
            },
            pressedContainerColor: Color = focusedContainerColor,
            pressedContentColor: Color = focusedContentColor,
            disabledContainerColor: Color = if (Platform.rememberIsTv()) {
                Platform.colorScheme().surfaceVariant.copy(alpha = 0.4f)
            } else {
                Platform.colorScheme().onSurface.copy(alpha = 0.12f)
            },
            disabledContentColor: Color = if (Platform.rememberIsTv()) {
                Platform.colorScheme().onSurface.copy(alpha = 0.4f)
            } else {
                Platform.colorScheme().onSurface.copy(alpha = 0.38f)
            },
        ) = PlatformButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            focusedContainerColor = focusedContainerColor,
            focusedContentColor = focusedContentColor,
            pressedContainerColor = pressedContainerColor,
            pressedContentColor = pressedContentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor,
        )

        @Composable
        fun icon(
            containerColor: Color = if (Platform.rememberIsTv()) {
                Platform.colorScheme().surfaceVariant.copy(alpha = 0.8f)
            } else {
                Color.Transparent
            },
            contentColor: Color = if (Platform.rememberIsTv()) {
                Platform.colorScheme().onSurface
            } else {
                Platform.localContentColor()
            },
            focusedContainerColor: Color = if (Platform.rememberIsTv()) {
                Platform.colorScheme().onSurface
            } else {
                containerColor
            },
            focusedContentColor: Color = if (Platform.rememberIsTv()) {
                Platform.colorScheme().inverseOnSurface
            } else {
                contentColor
            },
            pressedContainerColor: Color = focusedContainerColor,
            pressedContentColor: Color = focusedContentColor,
            disabledContainerColor: Color = if (Platform.rememberIsTv()) {
                Platform.colorScheme().surfaceVariant.copy(alpha = 0.4f)
            } else {
                Color.Transparent
            },
            disabledContentColor: Color = if (Platform.rememberIsTv()) {
                contentColor
            } else {
                Platform.localContentColor().copy(alpha = 0.38f)
            },
        ) = PlatformButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            focusedContainerColor = focusedContainerColor,
            focusedContentColor = focusedContentColor,
            pressedContainerColor = pressedContainerColor,
            pressedContentColor = pressedContentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor,
        )
    }
}