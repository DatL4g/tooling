package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import dev.datlag.tooling.Platform

@Immutable
class PlatformButtonBorder(
    val border: PlatformBorder,
    val focusedBorder: PlatformBorder,
    val pressedBorder: PlatformBorder,
    val disabledBorder: PlatformBorder,
    val focusedDisabledBorder: PlatformBorder
) {

    fun borderStroke(
        enabled: Boolean,
        focused: Boolean,
        pressed: Boolean
    ): BorderStroke {
        return when {
            enabled && pressed -> pressedBorder.border
            enabled && focused -> focusedBorder.border
            enabled -> border.border
            !enabled && focused -> focusedDisabledBorder.border
            !enabled -> disabledBorder.border
            else -> disabledBorder.border
        }
    }

    fun borderStrokeOrNull(
        enabled: Boolean,
        focused: Boolean,
        pressed: Boolean
    ): BorderStroke? {
        val border = borderStroke(enabled, focused, pressed)

        return if (border.width <= 0.dp) {
            null
        } else {
            border
        }
    }

    @Composable
    fun borderStroke(
        enabled: Boolean,
        interactionSource: MutableInteractionSource
    ): BorderStroke {
        val focused by interactionSource.collectIsFocusedAsState()
        val pressed by interactionSource.collectIsPressedAsState()

        return borderStroke(
            enabled = enabled,
            focused = focused,
            pressed = pressed
        )
    }

    @Composable
    fun borderStrokeOrNull(
        enabled: Boolean,
        interactionSource: MutableInteractionSource
    ): BorderStroke? {
        val focused by interactionSource.collectIsFocusedAsState()
        val pressed by interactionSource.collectIsPressedAsState()

        return borderStrokeOrNull(
            enabled = enabled,
            focused = focused,
            pressed = pressed
        )
    }

    companion object {
        @Composable
        fun default(
            border: PlatformBorder = PlatformBorder.None,
            focusedBorder: PlatformBorder = border,
            pressedBorder: PlatformBorder = focusedBorder,
            disabledBorder: PlatformBorder = border,
            focusedDisabledBorder: PlatformBorder = PlatformBorder(
                border = BorderStroke(
                    width = 1.5.dp,
                    color = Platform.colorScheme().onSurfaceVariant.copy(alpha = 0.2f)
                ),
                shape = CircleShape
            )
        ) = PlatformButtonBorder(
            border = border,
            focusedBorder = focusedBorder,
            pressedBorder = pressedBorder,
            disabledBorder = disabledBorder,
            focusedDisabledBorder = focusedDisabledBorder
        )

        @Composable
        fun icon(
            border: PlatformBorder = PlatformBorder.None,
            focusedBorder: PlatformBorder = border,
            pressedBorder: PlatformBorder = focusedBorder,
            disabledBorder: PlatformBorder = border,
            focusedDisabledBorder: PlatformBorder = PlatformBorder(
                border = BorderStroke(
                    width = 2.dp,
                    color = Platform.colorScheme().outline.copy(alpha = 0.2f)
                ),
                shape = CircleShape
            )
        ) = PlatformButtonBorder(
            border = border,
            focusedBorder = focusedBorder,
            pressedBorder = pressedBorder,
            disabledBorder = disabledBorder,
            focusedDisabledBorder = focusedDisabledBorder
        )
    }
}