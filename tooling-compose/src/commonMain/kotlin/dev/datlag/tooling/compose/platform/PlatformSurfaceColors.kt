package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.SelectableChipColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import dev.datlag.tooling.Platform

sealed interface PlatformSurfaceColors {
    @Immutable
    class Clickable(
        val containerColor: Color,
        val contentColor: Color,
        val focusedContainerColor: Color,
        val focusedContentColor: Color,
        val pressedContainerColor: Color,
        val pressedContentColor: Color,
        val disabledContainerColor: Color,
        val disabledContentColor: Color,
    ) : PlatformSurfaceColors {

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

        @Composable
        fun containerColor(
            enabled: Boolean,
            interactionSource: MutableInteractionSource
        ): Color {
            val focused by interactionSource.collectIsFocusedAsState()
            val pressed by interactionSource.collectIsPressedAsState()

            return containerColor(
                enabled = enabled,
                focused = focused,
                pressed = pressed
            )
        }

        @Composable
        fun contentColor(
            enabled: Boolean,
            interactionSource: MutableInteractionSource
        ): Color {
            val focused by interactionSource.collectIsFocusedAsState()
            val pressed by interactionSource.collectIsPressedAsState()

            return contentColor(
                enabled = enabled,
                focused = focused,
                pressed = pressed
            )
        }

        companion object {
            @Composable
            fun default(
                containerColor: Color = Platform.colorScheme().surface,
                contentColor: Color = Platform.contentColorFor(containerColor),
                focusedContainerColor: Color = if (Platform.rememberIsTv()) {
                    Platform.colorScheme().inverseSurface
                } else {
                    containerColor
                },
                focusedContentColor: Color = if (Platform.rememberIsTv()) {
                    Platform.contentColorFor(focusedContainerColor)
                } else {
                    contentColor
                },
                pressedContainerColor: Color = focusedContainerColor,
                pressedContentColor: Color = Platform.contentColorFor(pressedContainerColor),
                disabledContainerColor: Color = if (Platform.rememberIsTv()) {
                    Platform.colorScheme().surfaceVariant.copy(alpha = 0.4f)
                } else {
                    containerColor
                },
                disabledContentColor: Color = if (Platform.rememberIsTv()) {
                    Platform.colorScheme().onSurface
                } else {
                    contentColor
                },
            ) = Clickable(
                containerColor = containerColor,
                contentColor = contentColor,
                focusedContainerColor = focusedContainerColor,
                focusedContentColor = focusedContentColor,
                pressedContainerColor = pressedContainerColor,
                pressedContentColor = pressedContentColor,
                disabledContainerColor = disabledContainerColor,
                disabledContentColor = disabledContentColor,
            )
        }
    }

    @Immutable
    class Selectable(
        val containerColor: Color,
        val contentColor: Color,
        val focusedContainerColor: Color,
        val focusedContentColor: Color,
        val pressedContainerColor: Color,
        val pressedContentColor: Color,
        val selectedContainerColor: Color,
        val selectedContentColor: Color,
        val disabledContainerColor: Color,
        val disabledContentColor: Color,
        val focusedSelectedContainerColor: Color,
        val focusedSelectedContentColor: Color,
        val pressedSelectedContainerColor: Color,
        val pressedSelectedContentColor: Color
    ) : PlatformSurfaceColors {

        fun containerColor(
            enabled: Boolean,
            focused: Boolean,
            pressed: Boolean,
            selected: Boolean,
        ): Color {
            return when {
                enabled && selected && pressed -> pressedSelectedContainerColor
                enabled && selected && focused -> focusedSelectedContainerColor
                enabled && selected -> selectedContainerColor
                enabled && pressed -> pressedContainerColor
                enabled && focused -> focusedContainerColor
                enabled -> containerColor
                else -> disabledContainerColor
            }
        }

        fun contentColor(
            enabled: Boolean,
            focused: Boolean,
            pressed: Boolean,
            selected: Boolean,
        ): Color {
            return when {
                enabled && selected && pressed -> pressedSelectedContentColor
                enabled && selected && focused -> focusedSelectedContentColor
                enabled && selected -> selectedContentColor
                enabled && pressed -> pressedContentColor
                enabled && focused -> focusedContentColor
                enabled -> contentColor
                else -> disabledContentColor
            }
        }

        @Composable
        fun containerColor(
            enabled: Boolean,
            selected: Boolean,
            interactionSource: MutableInteractionSource
        ): Color {
            val focused by interactionSource.collectIsFocusedAsState()
            val pressed by interactionSource.collectIsPressedAsState()

            return containerColor(
                enabled = enabled,
                focused = focused,
                pressed = pressed,
                selected = selected
            )
        }

        @Composable
        fun contentColor(
            enabled: Boolean,
            selected: Boolean,
            interactionSource: MutableInteractionSource
        ): Color {
            val focused by interactionSource.collectIsFocusedAsState()
            val pressed by interactionSource.collectIsPressedAsState()

            return contentColor(
                enabled = enabled,
                focused = focused,
                pressed = pressed,
                selected = selected
            )
        }

        companion object {

            @Composable
            fun default(
                containerColor: Color = Platform.colorScheme().surface,
                contentColor: Color = Platform.contentColorFor(containerColor),
                focusedContainerColor: Color = if (Platform.rememberIsTv()) {
                    Platform.colorScheme().inverseSurface
                } else {
                    containerColor
                },
                focusedContentColor: Color = Platform.contentColorFor(focusedContainerColor),
                pressedContainerColor: Color = focusedContainerColor,
                pressedContentColor: Color = Platform.contentColorFor(pressedContainerColor),
                selectedContainerColor: Color = if (Platform.rememberIsTv()) {
                    Platform.colorScheme().inverseSurface.copy(
                        alpha = 0.5f
                    )
                } else {
                    containerColor
                },
                selectedContentColor: Color = if (Platform.rememberIsTv()) {
                    Platform.colorScheme().inverseOnSurface
                } else {
                    contentColor
                },
                disabledContainerColor: Color = if (Platform.rememberIsTv()) {
                    Platform.colorScheme().surfaceVariant.copy(
                        alpha = 0.4f
                    )
                } else {
                    containerColor
                },
                disabledContentColor: Color = if (Platform.rememberIsTv()) {
                    Platform.colorScheme().onSurface
                } else {
                    contentColor
                },
                focusedSelectedContainerColor: Color = if (Platform.rememberIsTv()) {
                    Platform.colorScheme().inverseSurface.copy(alpha = 0.5f)
                } else {
                    selectedContainerColor
                },
                focusedSelectedContentColor: Color = if (Platform.rememberIsTv()) {
                    Platform.colorScheme().inverseOnSurface
                } else {
                    selectedContentColor
                },
                pressedSelectedContainerColor: Color = focusedSelectedContainerColor,
                pressedSelectedContentColor: Color = focusedSelectedContentColor,
            ) = Selectable(
                containerColor = containerColor,
                contentColor = contentColor,
                focusedContainerColor = focusedContainerColor,
                focusedContentColor = focusedContentColor,
                pressedContainerColor = pressedContainerColor,
                pressedContentColor = pressedContentColor,
                selectedContainerColor = selectedContainerColor,
                selectedContentColor = selectedContentColor,
                disabledContainerColor = disabledContainerColor,
                disabledContentColor = disabledContentColor,
                focusedSelectedContainerColor = focusedSelectedContainerColor,
                focusedSelectedContentColor = focusedSelectedContentColor,
                pressedSelectedContainerColor = pressedSelectedContainerColor,
                pressedSelectedContentColor = pressedSelectedContentColor,
            )
        }
    }
}