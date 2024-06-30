package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue

@Immutable
class PlatformSelectableChipGlow(
    val glow: PlatformGlow,
    val focusedGlow: PlatformGlow,
    val pressedGlow: PlatformGlow,
    val selectedGlow: PlatformGlow,
    val focusedSelectedGlow: PlatformGlow,
    val pressedSelectedGlow: PlatformGlow
) {
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
        fun filter(
            glow: PlatformGlow = PlatformGlow.None,
            focusedGlow: PlatformGlow = glow,
            pressedGlow: PlatformGlow = glow,
            selectedGlow: PlatformGlow = glow,
            focusedSelectedGlow: PlatformGlow = focusedGlow,
            pressedSelectedGlow: PlatformGlow = glow
        ) = PlatformSelectableChipGlow(
            glow = glow,
            focusedGlow = focusedGlow,
            pressedGlow = pressedGlow,
            selectedGlow = selectedGlow,
            focusedSelectedGlow = focusedSelectedGlow,
            pressedSelectedGlow = pressedSelectedGlow
        )

        fun input(
            glow: PlatformGlow = PlatformGlow.None,
            focusedGlow: PlatformGlow = glow,
            pressedGlow: PlatformGlow = glow,
            selectedGlow: PlatformGlow = glow,
            focusedSelectedGlow: PlatformGlow = focusedGlow,
            pressedSelectedGlow: PlatformGlow = glow
        ) = PlatformSelectableChipGlow(
            glow = glow,
            focusedGlow = focusedGlow,
            pressedGlow = pressedGlow,
            selectedGlow = selectedGlow,
            focusedSelectedGlow = focusedSelectedGlow,
            pressedSelectedGlow = pressedSelectedGlow
        )
    }
}