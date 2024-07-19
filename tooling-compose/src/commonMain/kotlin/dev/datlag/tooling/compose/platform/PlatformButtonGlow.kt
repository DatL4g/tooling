package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue

@Immutable
class PlatformButtonGlow(
    val glow: PlatformGlow,
    val focusedGlow: PlatformGlow,
    val pressedGlow: PlatformGlow
) {
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
        ) = PlatformButtonGlow(
            glow = glow,
            focusedGlow = focusedGlow,
            pressedGlow = pressedGlow
        )

        fun icon(
            glow: PlatformGlow = PlatformGlow.None,
            focusedGlow: PlatformGlow = glow,
            pressedGlow: PlatformGlow = glow
        ) = PlatformButtonGlow(
            glow = glow,
            focusedGlow = focusedGlow,
            pressedGlow = pressedGlow
        )
    }
}