package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Shape
import dev.datlag.tooling.Platform

@Immutable
class PlatformClickableChipShape(
    val shape: Shape,
    val focusedShape: Shape = shape,
    val pressedShape: Shape = shape,
    val disabledShape: Shape = shape,
    val focusedDisabledShape: Shape = disabledShape
) {
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
        fun assist(
            shape: Shape = Platform.shapes().small,
            focusedShape: Shape = shape,
            pressedShape: Shape = shape,
            disabledShape: Shape = shape,
            focusedDisabledShape: Shape = disabledShape
        ) = PlatformClickableChipShape(
            shape = shape,
            focusedShape = focusedShape,
            pressedShape = pressedShape,
            disabledShape = disabledShape,
            focusedDisabledShape = focusedDisabledShape
        )

        @Composable
        fun suggestion(
            shape: Shape = Platform.shapes().small,
            focusedShape: Shape = shape,
            pressedShape: Shape = shape,
            disabledShape: Shape = shape,
            focusedDisabledShape: Shape = disabledShape
        ) = PlatformClickableChipShape(
            shape = shape,
            focusedShape = focusedShape,
            pressedShape = pressedShape,
            disabledShape = disabledShape,
            focusedDisabledShape = focusedDisabledShape
        )
    }
}