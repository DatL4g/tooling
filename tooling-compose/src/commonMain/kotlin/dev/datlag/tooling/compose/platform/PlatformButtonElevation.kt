package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Stable
class PlatformButtonElevation(
    val defaultElevation: Dp,
    val pressedElevation: Dp,
    val focusedElevation: Dp,
    val disabledElevation: Dp,
) {
    @Composable
    fun elevation() = ButtonDefaults.buttonElevation(
        defaultElevation = defaultElevation,
        pressedElevation = pressedElevation,
        focusedElevation = focusedElevation,
        disabledElevation = disabledElevation
    )

    @Composable
    fun elevation(
        enabled: Boolean,
        interactionSource: MutableInteractionSource
    ): Dp {
        val focused by interactionSource.collectIsFocusedAsState()
        val pressed by interactionSource.collectIsPressedAsState()

        return when {
            !enabled -> disabledElevation
            pressed -> pressedElevation
            focused -> focusedElevation
            else -> defaultElevation
        }
    }

    companion object {
        @Composable
        fun filled(
            defaultElevation: Dp = 0.dp,
            pressedElevation: Dp = 0.dp,
            focusedElevation: Dp = 0.dp,
            disabledElevation: Dp = 0.dp
        ) = PlatformButtonElevation(
            defaultElevation = defaultElevation,
            pressedElevation = pressedElevation,
            focusedElevation = focusedElevation,
            disabledElevation = disabledElevation
        )
    }
}