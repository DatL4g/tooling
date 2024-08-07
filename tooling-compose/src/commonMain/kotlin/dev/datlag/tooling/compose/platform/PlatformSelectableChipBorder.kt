package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.InputChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import dev.datlag.tooling.Platform

@Immutable
class PlatformSelectableChipBorder internal constructor(
    val border: PlatformBorder,
    val focusedBorder: PlatformBorder,
    val pressedBorder: PlatformBorder,
    val selectedBorder: PlatformBorder,
    val disabledBorder: PlatformBorder,
    val focusedSelectedBorder: PlatformBorder,
    val focusedDisabledBorder: PlatformBorder,
    val pressedSelectedBorder: PlatformBorder,
    val selectedDisabledBorder: PlatformBorder,
    val focusedSelectedDisabledBorder: PlatformBorder
) {

    fun borderStroke(
        enabled: Boolean,
        focused: Boolean,
        pressed: Boolean,
        selected: Boolean,
    ): BorderStroke {
        return when {
            enabled && selected && pressed -> pressedSelectedBorder.border
            enabled && selected && focused -> focusedSelectedBorder.border
            enabled && selected -> selectedBorder.border
            enabled && pressed -> pressedBorder.border
            enabled && focused -> focusedBorder.border
            enabled -> border.border
            !enabled && selected && focused -> focusedSelectedDisabledBorder.border
            !enabled && selected -> selectedDisabledBorder.border
            !enabled && focused -> focusedDisabledBorder.border
            else -> disabledBorder.border
        }
    }

    fun borderStrokeOrNull(
        enabled: Boolean,
        focused: Boolean,
        pressed: Boolean,
        selected: Boolean,
    ): BorderStroke? {
        val border = borderStroke(enabled, focused, pressed, selected)

        return if (border.width <= 0.dp) {
            null
        } else {
            border
        }
    }

    @Composable
    fun borderStroke(
        enabled: Boolean,
        selected: Boolean,
        interactionSource: MutableInteractionSource
    ): BorderStroke {
        val focused by interactionSource.collectIsFocusedAsState()
        val pressed by interactionSource.collectIsPressedAsState()

        return borderStroke(
            enabled = enabled,
            focused = focused,
            pressed = pressed,
            selected = selected
        )
    }

    @Composable
    fun borderStrokeOrNull(
        enabled: Boolean,
        selected: Boolean,
        interactionSource: MutableInteractionSource
    ): BorderStroke? {
        val focused by interactionSource.collectIsFocusedAsState()
        val pressed by interactionSource.collectIsPressedAsState()

        return borderStrokeOrNull(
            enabled = enabled,
            focused = focused,
            pressed = pressed,
            selected = selected
        )
    }

    companion object {
        @Composable
        fun filter(
            border: PlatformBorder = if (Platform.rememberIsTv()) {
                PlatformBorder(
                    border = BorderStroke(
                        width = 1.dp,
                        color = Platform.colorScheme().outline
                    ),
                    shape = Platform.shapes().small
                )
            } else {
                PlatformBorder(
                    border = FilterChipDefaults.filterChipBorder(enabled = true, selected = false),
                    shape = Platform.shapes().small
                )
            },
            focusedBorder: PlatformBorder = if (Platform.rememberIsTv()) {
                PlatformBorder.None
            } else {
                border
            },
            pressedBorder: PlatformBorder = focusedBorder,
            selectedBorder: PlatformBorder = if (Platform.rememberIsTv()) {
                PlatformBorder(
                    border = BorderStroke(
                        width = 1.dp,
                        color = Platform.colorScheme().secondary
                    ),
                    shape = Platform.shapes().small
                )
            } else {
                PlatformBorder(
                    border = FilterChipDefaults.filterChipBorder(enabled = true, selected = true),
                    shape = Platform.shapes().small
                )
            },
            disabledBorder: PlatformBorder = if (Platform.rememberIsTv()) {
                PlatformBorder(
                    border = BorderStroke(
                        width = 1.dp,
                        color = Platform.colorScheme().surfaceVariant
                    ),
                    shape = Platform.shapes().small
                )
            } else {
                PlatformBorder(
                    border = FilterChipDefaults.filterChipBorder(enabled = false, selected = false),
                    shape = Platform.shapes().small
                )
            },
            focusedSelectedBorder: PlatformBorder = if (Platform.rememberIsTv()) {
                PlatformBorder(
                    border = BorderStroke(
                        width = 1.1.dp,
                        color = Platform.colorScheme().onPrimaryContainer
                    ),
                    shape = Platform.shapes().small
                )
            } else {
                selectedBorder
            },
            focusedDisabledBorder: PlatformBorder = if (Platform.rememberIsTv()) {
                border
            } else {
                disabledBorder
            },
            pressedSelectedBorder: PlatformBorder = if (Platform.rememberIsTv()) {
                PlatformBorder.None
            } else {
                selectedBorder
            },
            selectedDisabledBorder: PlatformBorder = if (Platform.rememberIsTv()) {
                PlatformBorder.None
            } else {
                PlatformBorder(
                    border = FilterChipDefaults.filterChipBorder(enabled = false, selected = true),
                    shape = Platform.shapes().small
                )
            },
            focusedSelectedDisabledBorder: PlatformBorder = if (Platform.rememberIsTv()) {
                border
            } else {
                selectedDisabledBorder
            }
        ) = PlatformSelectableChipBorder(
            border = border,
            focusedBorder = focusedBorder,
            pressedBorder = pressedBorder,
            selectedBorder = selectedBorder,
            disabledBorder = disabledBorder,
            focusedSelectedBorder = focusedSelectedBorder,
            focusedDisabledBorder = focusedDisabledBorder,
            pressedSelectedBorder = pressedSelectedBorder,
            selectedDisabledBorder = selectedDisabledBorder,
            focusedSelectedDisabledBorder = focusedSelectedDisabledBorder,
        )

        @Composable
        fun input(
            hasAvatar: Boolean,
            border: PlatformBorder = if (Platform.rememberIsTv()) {
                PlatformBorder(
                    border = BorderStroke(
                        width = 1.dp,
                        color = Platform.colorScheme().outline
                    ),
                    shape = if (hasAvatar) Platform.shapes().medium else Platform.shapes().small
                )
            } else {
                PlatformBorder(
                    border = InputChipDefaults.inputChipBorder(enabled = true, selected = false),
                    shape = if (hasAvatar) Platform.shapes().medium else Platform.shapes().small
                )
            },
            focusedBorder: PlatformBorder = if (Platform.rememberIsTv()) {
                PlatformBorder.None
            } else {
                border
            },
            pressedBorder: PlatformBorder = focusedBorder,
            selectedBorder: PlatformBorder = if (Platform.rememberIsTv()) {
                PlatformBorder(
                    border = BorderStroke(
                        width = 1.dp,
                        color = Platform.colorScheme().secondary
                    ),
                    shape = if (hasAvatar) Platform.shapes().medium else Platform.shapes().small
                )
            } else {
                PlatformBorder(
                    border = InputChipDefaults.inputChipBorder(enabled = true, selected = true),
                    shape = if (hasAvatar) Platform.shapes().medium else Platform.shapes().small
                )
            },
            disabledBorder: PlatformBorder = if (Platform.rememberIsTv()) {
                PlatformBorder(
                    border = BorderStroke(
                        width = 1.dp,
                        color = Platform.colorScheme().surfaceVariant
                    ),
                    shape = if (hasAvatar) Platform.shapes().medium else Platform.shapes().small
                )
            } else {
                PlatformBorder(
                    border = InputChipDefaults.inputChipBorder(enabled = false, selected = false),
                    shape = if (hasAvatar) Platform.shapes().medium else Platform.shapes().small
                )
            },
            focusedSelectedBorder: PlatformBorder = if (Platform.rememberIsTv()) {
                PlatformBorder(
                    border = BorderStroke(
                        width = 1.1.dp,
                        color = Platform.colorScheme().onPrimaryContainer
                    ),
                    shape = if (hasAvatar) Platform.shapes().medium else Platform.shapes().small
                )
            } else {
                selectedBorder
            },
            focusedDisabledBorder: PlatformBorder = if (Platform.rememberIsTv()) {
                border
            } else {
                disabledBorder
            },
            pressedSelectedBorder: PlatformBorder = if (Platform.rememberIsTv()) {
                PlatformBorder.None
            } else {
                selectedBorder
            },
            selectedDisabledBorder: PlatformBorder = if (Platform.rememberIsTv()) {
                PlatformBorder.None
            } else {
                PlatformBorder(
                    border = InputChipDefaults.inputChipBorder(enabled = false, selected = true),
                    shape = if (hasAvatar) Platform.shapes().medium else Platform.shapes().small
                )
            },
            focusedSelectedDisabledBorder: PlatformBorder = if (Platform.rememberIsTv()) {
                border
            } else {
                selectedDisabledBorder
            }
        ) = PlatformSelectableChipBorder(
            border = border,
            focusedBorder = focusedBorder,
            pressedBorder = pressedBorder,
            selectedBorder = selectedBorder,
            disabledBorder = disabledBorder,
            focusedSelectedBorder = focusedSelectedBorder,
            focusedDisabledBorder = focusedDisabledBorder,
            pressedSelectedBorder = pressedSelectedBorder,
            selectedDisabledBorder = selectedDisabledBorder,
            focusedSelectedDisabledBorder = focusedSelectedDisabledBorder,
        )
    }
}