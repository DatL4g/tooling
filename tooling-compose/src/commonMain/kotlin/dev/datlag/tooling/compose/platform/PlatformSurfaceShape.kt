package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import dev.datlag.tooling.Platform

sealed interface PlatformSurfaceShape {

    @Immutable
    class Clickable(
        val shape: Shape,
        val focusedShape: Shape,
        val pressedShape: Shape,
        val disabledShape: Shape,
        val focusedDisabledShape: Shape
    ) : PlatformSurfaceShape {
        fun shape(
            enabled: Boolean,
            focused: Boolean,
            pressed: Boolean
        ): Shape {
            return when {
                enabled && pressed -> pressedShape
                enabled && focused -> focusedShape
                enabled -> shape
                !enabled && focused -> focusedDisabledShape
                !enabled -> disabledShape
                else -> disabledShape
            }
        }

        @Composable
        fun shape(
            enabled: Boolean,
            interactionSource: MutableInteractionSource
        ): Shape {
            val focused by interactionSource.collectIsFocusedAsState()
            val pressed by interactionSource.collectIsPressedAsState()

            return shape(
                enabled = enabled,
                focused = focused,
                pressed = pressed
            )
        }

        companion object {
            @Composable
            fun default(
                shape: Shape = if (Platform.rememberIsTv()) {
                    Platform.shapes().medium
                } else {
                    RectangleShape
                },
                focusedShape: Shape = shape,
                pressedShape: Shape = shape,
                disabledShape: Shape = shape,
                focusedDisabledShape: Shape = disabledShape
            ) = Clickable(
                shape = shape,
                focusedShape = focusedShape,
                pressedShape = pressedShape,
                disabledShape = disabledShape,
                focusedDisabledShape = focusedDisabledShape
            )
        }
    }

    @Immutable
    class Selectable(
        val shape: Shape,
        val focusedShape: Shape = shape,
        val pressedShape: Shape = shape,
        val selectedShape: Shape = shape,
        val disabledShape: Shape = shape,
        val focusedSelectedShape: Shape = shape,
        val focusedDisabledShape: Shape = disabledShape,
        val pressedSelectedShape: Shape = shape,
        val selectedDisabledShape: Shape = disabledShape,
        val focusedSelectedDisabledShape: Shape = disabledShape
    ) : PlatformSurfaceShape {

        fun shape(
            enabled: Boolean,
            focused: Boolean,
            pressed: Boolean,
            selected: Boolean
        ): Shape {
            return when {
                enabled && selected && pressed -> pressedSelectedShape
                enabled && selected && focused -> focusedSelectedShape
                enabled && selected -> selectedShape
                enabled && pressed -> pressedShape
                enabled && focused -> focusedShape
                enabled -> shape
                !enabled && selected && focused -> focusedSelectedDisabledShape
                !enabled && selected -> selectedDisabledShape
                !enabled && focused -> focusedDisabledShape
                else -> disabledShape
            }
        }

        @Composable
        fun shape(
            enabled: Boolean,
            selected: Boolean,
            interactionSource: MutableInteractionSource
        ): Shape {
            val focused by interactionSource.collectIsFocusedAsState()
            val pressed by interactionSource.collectIsPressedAsState()

            return shape(
                enabled = enabled,
                focused = focused,
                pressed = pressed,
                selected = selected
            )
        }

        companion object {
            @Composable
            fun default(
                shape: Shape = if (Platform.rememberIsTv()) {
                    Platform.shapes().medium
                } else {
                    RectangleShape
                },
                focusedShape: Shape = shape,
                pressedShape: Shape = shape,
                selectedShape: Shape = shape,
                disabledShape: Shape = shape,
                focusedSelectedShape: Shape = shape,
                focusedDisabledShape: Shape = disabledShape,
                pressedSelectedShape: Shape = shape,
                selectedDisabledShape: Shape = disabledShape,
                focusedSelectedDisabledShape: Shape = disabledShape
            ) = Selectable(
                shape = shape,
                focusedShape = focusedShape,
                pressedShape = pressedShape,
                selectedShape = selectedShape,
                disabledShape = disabledShape,
                focusedSelectedShape = focusedSelectedShape,
                focusedDisabledShape = focusedDisabledShape,
                pressedSelectedShape = pressedSelectedShape,
                selectedDisabledShape = selectedDisabledShape,
                focusedSelectedDisabledShape = focusedSelectedDisabledShape
            )
        }
    }
}