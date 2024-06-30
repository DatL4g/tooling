package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue

@Immutable
class PlatformClickableChipScale(
    val scale: Float = 1F,
    val focusedScale: Float = 1.1F,
    val pressedScale: Float = scale,
    val disabledScale: Float = scale,
    val focusedDisabledScale: Float = disabledScale
) {
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
        fun assist(
            scale: Float = 1F,
            focusedScale: Float = 1.1F,
            pressedScale: Float = scale,
            disabledScale: Float = scale,
            focusedDisabledScale: Float = disabledScale
        ) = PlatformClickableChipScale(
            scale = scale,
            focusedScale = focusedScale,
            pressedScale = pressedScale,
            disabledScale = disabledScale,
            focusedDisabledScale = focusedDisabledScale
        )

        fun suggestion(
            scale: Float = 1F,
            focusedScale: Float = 1.1F,
            pressedScale: Float = scale,
            disabledScale: Float = scale,
            focusedDisabledScale: Float = disabledScale
        ) = PlatformClickableChipScale(
            scale = scale,
            focusedScale = focusedScale,
            pressedScale = pressedScale,
            disabledScale = disabledScale,
            focusedDisabledScale = focusedDisabledScale
        )
    }
}