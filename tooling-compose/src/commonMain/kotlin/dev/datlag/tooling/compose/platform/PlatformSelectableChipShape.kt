package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Shape
import dev.datlag.tooling.Platform

@Immutable
class PlatformSelectableChipShape(
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
) {

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
        fun filter(
            shape: Shape = Platform.shapes().small,
            focusedShape: Shape = shape,
            pressedShape: Shape = shape,
            selectedShape: Shape = shape,
            disabledShape: Shape = shape,
            focusedSelectedShape: Shape = shape,
            focusedDisabledShape: Shape = disabledShape,
            pressedSelectedShape: Shape = shape,
            selectedDisabledShape: Shape = disabledShape,
            focusedSelectedDisabledShape: Shape = disabledShape
        ) = PlatformSelectableChipShape(
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

        @Composable
        fun input(
            hasAvatar: Boolean,
            shape: Shape = if (hasAvatar) Platform.shapes().medium else Platform.shapes().small,
            focusedShape: Shape = shape,
            pressedShape: Shape = shape,
            selectedShape: Shape = shape,
            disabledShape: Shape = shape,
            focusedSelectedShape: Shape = shape,
            focusedDisabledShape: Shape = disabledShape,
            pressedSelectedShape: Shape = shape,
            selectedDisabledShape: Shape = disabledShape,
            focusedSelectedDisabledShape: Shape = disabledShape
        ) = PlatformSelectableChipShape(
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