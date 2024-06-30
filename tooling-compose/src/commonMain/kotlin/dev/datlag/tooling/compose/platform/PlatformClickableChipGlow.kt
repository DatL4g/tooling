package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue

@Immutable
class PlatformClickableChipGlow internal constructor(
    internal val glow: PlatformGlow,
    internal val focusedGlow: PlatformGlow,
    internal val pressedGlow: PlatformGlow
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
        fun assist(
            glow: PlatformGlow = PlatformGlow.None,
            focusedGlow: PlatformGlow = glow,
            pressedGlow: PlatformGlow = glow
        ) = PlatformClickableChipGlow(
            glow = glow,
            focusedGlow = focusedGlow,
            pressedGlow = pressedGlow
        )

        fun suggestion(
            glow: PlatformGlow = PlatformGlow.None,
            focusedGlow: PlatformGlow = glow,
            pressedGlow: PlatformGlow = glow
        ) = PlatformClickableChipGlow(
            glow = glow,
            focusedGlow = focusedGlow,
            pressedGlow = pressedGlow
        )
    }
}