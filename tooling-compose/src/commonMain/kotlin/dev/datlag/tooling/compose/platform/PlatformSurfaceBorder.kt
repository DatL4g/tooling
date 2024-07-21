package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.InputChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import dev.datlag.tooling.Platform

sealed interface PlatformSurfaceBorder {
    @Immutable
    class Clickable(
        val border: PlatformBorder,
        val focusedBorder: PlatformBorder,
        val pressedBorder: PlatformBorder,
        val disabledBorder: PlatformBorder,
        val focusedDisabledBorder: PlatformBorder
    ) : PlatformSurfaceBorder {

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
                        width = 2.dp,
                        color = Platform.colorScheme().outline
                    ),
                    inset = 0.dp,
                    shape = RoundedCornerShape(8.dp)
                )
            ) = Clickable(
                border = border,
                focusedBorder = focusedBorder,
                pressedBorder = pressedBorder,
                disabledBorder = disabledBorder,
                focusedDisabledBorder = focusedDisabledBorder
            )
        }
    }

    @Immutable
    class Selectable internal constructor(
        val border: PlatformBorder,
        val focusedBorder: PlatformBorder,
        val pressedBorder: PlatformBorder,
        val selectedBorder: PlatformBorder,
        val disabledBorder: PlatformBorder,
        val focusedSelectedBorder: PlatformBorder,
        val focusedDisabledBorder: PlatformBorder,
        val pressedSelectedBorder: PlatformBorder,
        val selectedDisabledBorder: PlatformBorder,
        val focusedSelectedDisabledBorder: PlatformBorder
    ) : PlatformSurfaceBorder {

        fun borderStroke(
            enabled: Boolean,
            focused: Boolean,
            pressed: Boolean,
            selected: Boolean,
        ): BorderStroke {
            return when {
                enabled && selected && pressed -> pressedSelectedBorder.border
                enabled && selected && focused -> focusedSelectedBorder.border
                enabled && selected -> selectedBorder.border
                enabled && pressed -> pressedBorder.border
                enabled && focused -> focusedBorder.border
                enabled -> border.border
                !enabled && selected && focused -> focusedSelectedDisabledBorder.border
                !enabled && selected -> selectedDisabledBorder.border
                !enabled && focused -> focusedDisabledBorder.border
                else -> disabledBorder.border
            }
        }

        fun borderStrokeOrNull(
            enabled: Boolean,
            focused: Boolean,
            pressed: Boolean,
            selected: Boolean,
        ): BorderStroke? {
            val border = borderStroke(enabled, focused, pressed, selected)

            return if (border.width <= 0.dp) {
                null
            } else {
                border
            }
        }

        @Composable
        fun borderStroke(
            enabled: Boolean,
            selected: Boolean,
            interactionSource: MutableInteractionSource
        ): BorderStroke {
            val focused by interactionSource.collectIsFocusedAsState()
            val pressed by interactionSource.collectIsPressedAsState()

            return borderStroke(
                enabled = enabled,
                focused = focused,
                pressed = pressed,
                selected = selected
            )
        }

        @Composable
        fun borderStrokeOrNull(
            enabled: Boolean,
            selected: Boolean,
            interactionSource: MutableInteractionSource
        ): BorderStroke? {
            val focused by interactionSource.collectIsFocusedAsState()
            val pressed by interactionSource.collectIsPressedAsState()

            return borderStrokeOrNull(
                enabled = enabled,
                focused = focused,
                pressed = pressed,
                selected = selected
            )
        }

        companion object {
            fun default(
                border: PlatformBorder = PlatformBorder.None,
                focusedBorder: PlatformBorder = border,
                pressedBorder: PlatformBorder = focusedBorder,
                selectedBorder: PlatformBorder = border,
                disabledBorder: PlatformBorder = border,
                focusedSelectedBorder: PlatformBorder = focusedBorder,
                focusedDisabledBorder: PlatformBorder = disabledBorder,
                pressedSelectedBorder: PlatformBorder = border,
                selectedDisabledBorder: PlatformBorder = disabledBorder,
                focusedSelectedDisabledBorder: PlatformBorder = disabledBorder
            ) = Selectable(
                border = border,
                focusedBorder = focusedBorder,
                pressedBorder = pressedBorder,
                selectedBorder = selectedBorder,
                disabledBorder = disabledBorder,
                focusedSelectedBorder = focusedSelectedBorder,
                focusedDisabledBorder = focusedDisabledBorder,
                pressedSelectedBorder = pressedSelectedBorder,
                selectedDisabledBorder = selectedDisabledBorder,
                focusedSelectedDisabledBorder = focusedSelectedDisabledBorder,
            )
        }
    }
}