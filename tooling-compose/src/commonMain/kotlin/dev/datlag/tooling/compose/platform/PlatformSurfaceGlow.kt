package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue

sealed interface PlatformSurfaceGlow {
    @Immutable
    class Clickable(
        val glow: PlatformGlow,
        val focusedGlow: PlatformGlow,
        val pressedGlow: PlatformGlow
    ) : PlatformSurfaceGlow {
        fun glow(
            focused: Boolean,
            pressed: Boolean
        ): PlatformGlow {
            return when {
                pressed -> pressedGlow
                focused -> focusedGlow
                else -> glow
            }
        }

        @Composable
        fun glow(
            interactionSource: MutableInteractionSource
        ): PlatformGlow {
            val focused by interactionSource.collectIsFocusedAsState()
            val pressed by interactionSource.collectIsPressedAsState()

            return glow(
                focused = focused,
                pressed = pressed
            )
        }

        companion object {
            fun default(
                glow: PlatformGlow = PlatformGlow.None,
                focusedGlow: PlatformGlow = glow,
                pressedGlow: PlatformGlow = glow
            ) = Clickable(
                glow = glow,
                focusedGlow = focusedGlow,
                pressedGlow = pressedGlow
            )
        }
    }

    @Immutable
    class Selectable(
        val glow: PlatformGlow,
        val focusedGlow: PlatformGlow,
        val pressedGlow: PlatformGlow,
        val selectedGlow: PlatformGlow,
        val focusedSelectedGlow: PlatformGlow,
        val pressedSelectedGlow: PlatformGlow
    ) : PlatformSurfaceGlow {
        fun glow(
            enabled: Boolean,
            focused: Boolean,
            pressed: Boolean,
            selected: Boolean
        ): PlatformGlow {
            return when {
                enabled && selected && pressed -> pressedSelectedGlow
                enabled && selected && focused -> focusedSelectedGlow
                enabled && selected -> selectedGlow
                enabled && pressed -> pressedGlow
                enabled && focused -> focusedGlow
                enabled -> glow
                else -> PlatformGlow.None
            }
        }

        @Composable
        fun glow(
            enabled: Boolean,
            selected: Boolean,
            interactionSource: MutableInteractionSource
        ): PlatformGlow {
            val focused by interactionSource.collectIsFocusedAsState()
            val pressed by interactionSource.collectIsPressedAsState()

            return glow(
                enabled = enabled,
                focused = focused,
                pressed = pressed,
                selected = selected
            )
        }

        companion object {
            fun default(
                glow: PlatformGlow = PlatformGlow.None,
                focusedGlow: PlatformGlow = glow,
                pressedGlow: PlatformGlow = glow,
                selectedGlow: PlatformGlow = glow,
                focusedSelectedGlow: PlatformGlow = focusedGlow,
                pressedSelectedGlow: PlatformGlow = glow
            ) = Selectable(
                glow = glow,
                focusedGlow = focusedGlow,
                pressedGlow = pressedGlow,
                selectedGlow = selectedGlow,
                focusedSelectedGlow = focusedSelectedGlow,
                pressedSelectedGlow = pressedSelectedGlow
            )
        }
    }
}