package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue

sealed interface PlatformSurfaceScale {
    @Immutable
    class Clickable(
        val scale: Float = 1F,
        val focusedScale: Float = 1F,
        val pressedScale: Float = 1F,
        val disabledScale: Float = 1F,
        val focusedDisabledScale: Float = 1F
    ) : PlatformSurfaceScale {

        fun scale(
            enabled: Boolean,
            focused: Boolean,
            pressed: Boolean,
        ): Float {
            return when {
                enabled && pressed -> pressedScale
                enabled && focused -> focusedScale
                enabled -> scale
                !enabled && focused -> focusedDisabledScale
                !enabled -> disabledScale
                else -> disabledScale
            }
        }

        @Composable
        fun scale(
            enabled: Boolean,
            interactionSource: MutableInteractionSource
        ): Float {
            val focused by interactionSource.collectIsFocusedAsState()
            val pressed by interactionSource.collectIsPressedAsState()

            return scale(
                enabled = enabled,
                focused = focused,
                pressed = pressed
            )
        }

        companion object {
            fun default(
                scale: Float = 1F,
                focusedScale: Float = 1.1F,
                pressedScale: Float = scale,
                disabledScale: Float = scale,
                focusedDisabledScale: Float = disabledScale
            ) = Clickable(
                scale = scale,
                focusedScale = focusedScale,
                pressedScale = pressedScale,
                disabledScale = disabledScale,
                focusedDisabledScale = focusedDisabledScale
            )
        }
    }

    @Immutable
    class Selectable internal constructor(
        val scale: Float = 1F,
        val focusedScale: Float = 1.1F,
        val pressedScale: Float = scale,
        val selectedScale: Float = scale,
        val disabledScale: Float = scale,
        val focusedSelectedScale: Float = focusedScale,
        val focusedDisabledScale: Float = disabledScale,
        val pressedSelectedScale: Float = scale,
        val selectedDisabledScale: Float = disabledScale,
        val focusedSelectedDisabledScale: Float = disabledScale
    ) : PlatformSurfaceScale {

        fun scale(
            enabled: Boolean,
            focused: Boolean,
            pressed: Boolean,
            selected: Boolean
        ): Float {
            return when {
                enabled && selected && pressed -> pressedSelectedScale
                enabled && selected && focused -> focusedSelectedScale
                enabled && selected -> selectedScale
                enabled && pressed -> pressedScale
                enabled && focused -> focusedScale
                enabled -> scale
                !enabled && selected && focused -> focusedSelectedDisabledScale
                !enabled && selected -> selectedDisabledScale
                !enabled && focused -> focusedDisabledScale
                else -> disabledScale
            }
        }

        @Composable
        fun scale(
            enabled: Boolean,
            selected: Boolean,
            interactionSource: MutableInteractionSource
        ): Float {
            val focused by interactionSource.collectIsFocusedAsState()
            val pressed by interactionSource.collectIsPressedAsState()

            return scale(
                enabled = enabled,
                focused = focused,
                pressed = pressed,
                selected = selected
            )
        }

        companion object {
            fun default(
                scale: Float = 1f,
                focusedScale: Float = 1.1f,
                pressedScale: Float = scale,
                selectedScale: Float = scale,
                disabledScale: Float = scale,
                focusedSelectedScale: Float = focusedScale,
                focusedDisabledScale: Float = disabledScale,
                pressedSelectedScale: Float = scale,
                selectedDisabledScale: Float = disabledScale,
                focusedSelectedDisabledScale: Float = disabledScale
            ) = Selectable(
                scale = scale,
                focusedScale = focusedScale,
                pressedScale = pressedScale,
                selectedScale = selectedScale,
                disabledScale = disabledScale,
                focusedSelectedScale = focusedSelectedScale,
                focusedDisabledScale = focusedDisabledScale,
                pressedSelectedScale = pressedSelectedScale,
                selectedDisabledScale = selectedDisabledScale,
                focusedSelectedDisabledScale = focusedSelectedDisabledScale
            )
        }
    }
}