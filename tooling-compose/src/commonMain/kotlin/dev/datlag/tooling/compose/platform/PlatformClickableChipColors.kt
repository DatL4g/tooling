package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ChipColors
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import dev.datlag.tooling.Platform

@Immutable
class PlatformClickableChipColors(
    val containerColor: Color,
    val contentColor: Color,
    val focusedContainerColor: Color,
    val focusedContentColor: Color,
    val pressedContainerColor: Color,
    val pressedContentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color
) {
    fun containerColor(
        enabled: Boolean,
        focused: Boolean,
        pressed: Boolean,
    ): Color {
        return when {
            pressed && enabled -> pressedContainerColor
            focused && enabled -> focusedContainerColor
            enabled -> containerColor
            else -> disabledContainerColor
        }
    }

    fun contentColor(
        enabled: Boolean,
        focused: Boolean,
        pressed: Boolean,
    ): Color {
        return when {
            pressed && enabled -> pressedContentColor
            focused && enabled -> focusedContentColor
            enabled -> contentColor
            else -> disabledContentColor
        }
    }

    fun colors(
        focused: Boolean,
        pressed: Boolean
    ): ChipColors {
        val enabledContainerColor = containerColor(
            enabled = true,
            focused = focused,
            pressed = pressed
        )
        val enabledContentColor = contentColor(
            enabled = true,
            focused = focused,
            pressed = pressed
        )

        val disabledContainerColor = containerColor(
            enabled = false,
            focused = focused,
            pressed = pressed
        )
        val disabledContentColor = contentColor(
            enabled = false,
            focused = focused,
            pressed = pressed
        )

        return ChipColors(
            containerColor = enabledContainerColor,
            labelColor = enabledContentColor,
            leadingIconContentColor = enabledContentColor,
            trailingIconContentColor = enabledContentColor,
            disabledContainerColor = disabledContainerColor,
            disabledLabelColor = disabledContentColor,
            disabledLeadingIconContentColor = disabledContentColor,
            disabledTrailingIconContentColor = disabledContentColor
        )
    }

    @Composable
    fun colors(
        interactionSource: MutableInteractionSource
    ): ChipColors {
        val focused by interactionSource.collectIsFocusedAsState()
        val pressed by interactionSource.collectIsPressedAsState()

        return colors(
            focused = focused,
            pressed = pressed
        )
    }

    companion object {

        private const val DisabledBackgroundColorOpacity = 0.2f
        private const val DisabledContentColorOpacity = 0.8f

        @Composable
        fun assist(
            containerColor: Color = Color.Transparent,
            contentColor: Color = if (Platform.rememberIsTv()) {
                Platform.colorScheme().onSurfaceVariant
            } else {
                Platform.colorScheme().onSurface
            },
            focusedContainerColor: Color = if (Platform.rememberIsTv()) {
                Platform.colorScheme().onSurface
            } else {
                containerColor
            },
            focusedContentColor: Color = if (Platform.rememberIsTv()) {
                Platform.colorScheme().inverseOnSurface
            } else {
                contentColor
            },
            pressedContainerColor: Color = if (Platform.rememberIsTv()) {
                Platform.colorScheme().onSurfaceVariant
            } else {
                containerColor
            },
            pressedContentColor: Color = if (Platform.rememberIsTv()) {
                Platform.colorScheme().surface
            } else {
                contentColor
            },
            disabledContainerColor: Color = if (Platform.rememberIsTv()) {
                Platform.colorScheme().surfaceVariant.copy(
                    alpha = DisabledBackgroundColorOpacity
                )
            } else {
                Color.Transparent
            },
            disabledContentColor: Color = if (Platform.rememberIsTv()) {
                Platform.colorScheme().outline.copy(
                    alpha = DisabledContentColorOpacity
                )
            } else {
                Platform.colorScheme().onSurface.copy(alpha = 0.38f)
            }
        ) = PlatformClickableChipColors(
            containerColor = containerColor,
            contentColor = contentColor,
            focusedContainerColor = focusedContainerColor,
            focusedContentColor = focusedContentColor,
            pressedContainerColor = pressedContainerColor,
            pressedContentColor = pressedContentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor
        )

        @Composable
        fun suggestion(
            containerColor: Color = Color.Transparent,
            contentColor: Color = Platform.colorScheme().onSurfaceVariant,
            focusedContainerColor: Color = if (Platform.rememberIsTv()) {
                Platform.colorScheme().onSurface
            } else {
                containerColor
            },
            focusedContentColor: Color = if (Platform.rememberIsTv()) {
                Platform.colorScheme().inverseOnSurface
            } else {
                contentColor
            },
            pressedContainerColor: Color = if (Platform.rememberIsTv()) {
                Platform.colorScheme().onSurfaceVariant
            } else {
                containerColor
            },
            pressedContentColor: Color = if (Platform.rememberIsTv()) {
                Platform.colorScheme().surface
            } else {
                contentColor
            },
            disabledContainerColor: Color = if (Platform.rememberIsTv()) {
                Platform.colorScheme().surfaceVariant.copy(
                    alpha = DisabledBackgroundColorOpacity
                )
            } else {
                Color.Transparent
            },
            disabledContentColor: Color = if (Platform.rememberIsTv()) {
                Platform.colorScheme().outline.copy(
                    alpha = DisabledContentColorOpacity
                )
            } else {
                Platform.colorScheme().onSurface.copy(alpha = 0.38f)
            }
        ) = PlatformClickableChipColors(
            containerColor = containerColor,
            contentColor = contentColor,
            focusedContainerColor = focusedContainerColor,
            focusedContentColor = focusedContentColor,
            pressedContainerColor = pressedContainerColor,
            pressedContentColor = pressedContentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor
        )
    }
}