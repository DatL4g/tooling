package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import dev.datlag.tooling.Platform

@Immutable
class PlatformCardShape(
    val shape: Shape,
    val focusedShape: Shape,
    val pressedShape: Shape
) {
    fun shape(
        enabled: Boolean,
        focused: Boolean,
        pressed: Boolean
    ): Shape {
        return when {
            enabled && pressed -> pressedShape
            enabled && focused -> focusedShape
            else -> shape
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
                RoundedCornerShape(8.dp)
            } else {
                CardDefaults.shape
            },
            focusedShape: Shape = shape,
            pressedShape: Shape = shape
        ) = PlatformCardShape(
            shape = shape,
            focusedShape = focusedShape,
            pressedShape = pressedShape
        )
    }
}