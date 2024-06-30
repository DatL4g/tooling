package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material3.ChipColors
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.SelectableChipColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import dev.datlag.tooling.Platform

@Immutable
class PlatformSelectableChipColors(
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
) {

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

    fun colors(
        focused: Boolean,
        pressed: Boolean
    ): SelectableChipColors {
        val enabledContainerColor = containerColor(
            enabled = true,
            focused = focused,
            pressed = pressed,
            selected = false
        )
        val enabledSelectedContainerColor = containerColor(
            enabled = true,
            focused = focused,
            pressed = pressed,
            selected = true
        )
        val enabledContentColor = contentColor(
            enabled = true,
            focused = focused,
            pressed = pressed,
            selected = false
        )
        val enabledSelectedContentColor = contentColor(
            enabled = true,
            focused = focused,
            pressed = pressed,
            selected = false
        )

        val disabledContainerColor = containerColor(
            enabled = false,
            focused = focused,
            pressed = pressed,
            selected = false
        )
        val disabledSelectedContainerColor = containerColor(
            enabled = false,
            focused = focused,
            pressed = pressed,
            selected = true
        )
        val disabledContentColor = contentColor(
            enabled = false,
            focused = focused,
            pressed = pressed,
            selected = false
        )

        return SelectableChipColors(
            containerColor = enabledContainerColor,
            labelColor = enabledContentColor,
            leadingIconColor = enabledContentColor,
            trailingIconColor = enabledContentColor,
            disabledContainerColor = disabledContainerColor,
            disabledLabelColor = disabledContentColor,
            disabledLeadingIconColor = disabledContentColor,
            disabledTrailingIconColor = disabledContentColor,
            selectedContainerColor = enabledSelectedContainerColor,
            disabledSelectedContainerColor = disabledSelectedContainerColor,
            selectedLabelColor = enabledSelectedContentColor,
            selectedLeadingIconColor = enabledSelectedContentColor,
            selectedTrailingIconColor = enabledSelectedContentColor
        )
    }

    @Composable
    fun colors(
        interactionSource: MutableInteractionSource
    ): SelectableChipColors {
        val focused by interactionSource.collectIsFocusedAsState()
        val pressed by interactionSource.collectIsPressedAsState()

        return colors(
            focused = focused,
            pressed = pressed
        )
    }

    companion object {

        private const val SelectedBackgroundColorOpacity = 0.4f
        private const val DisabledBackgroundColorOpacity = 0.2f
        private const val DisabledContentColorOpacity = 0.8f

        @Composable
        fun filter(
            containerColor: Color = Color.Transparent,
            contentColor: Color = Platform.colorScheme().onSurfaceVariant,
            focusedContainerColor: Color = Platform.colorScheme().onSurface,
            focusedContentColor: Color = Platform.colorScheme().inverseOnSurface,
            pressedContainerColor: Color = Platform.colorScheme().onSurfaceVariant,
            pressedContentColor: Color = Platform.colorScheme().surface,
            selectedContainerColor: Color = Platform.colorScheme().secondaryContainer.copy(
                alpha = SelectedBackgroundColorOpacity
            ),
            selectedContentColor: Color = Platform.colorScheme().onSecondaryContainer,
            disabledContainerColor: Color = Platform.colorScheme().surfaceVariant.copy(
                alpha = DisabledBackgroundColorOpacity
            ),
            disabledContentColor: Color = Platform.colorScheme().outline.copy(
                alpha = DisabledContentColorOpacity
            ),
            focusedSelectedContainerColor: Color = Platform.colorScheme().onPrimaryContainer,
            focusedSelectedContentColor: Color = Platform.colorScheme().onPrimary,
            pressedSelectedContainerColor: Color = Platform.colorScheme().secondary,
            pressedSelectedContentColor: Color = Platform.colorScheme().onSecondary,
        ) = PlatformSelectableChipColors(
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

        @Composable
        fun input(
            containerColor: Color = Color.Transparent,
            contentColor: Color = Platform.colorScheme().onSurfaceVariant,
            focusedContainerColor: Color = Platform.colorScheme().onSurface,
            focusedContentColor: Color = Platform.colorScheme().inverseOnSurface,
            pressedContainerColor: Color = Platform.colorScheme().onSurfaceVariant,
            pressedContentColor: Color = Platform.colorScheme().surface,
            selectedContainerColor: Color = Platform.colorScheme().secondaryContainer.copy(
                alpha = SelectedBackgroundColorOpacity
            ),
            selectedContentColor: Color = Platform.colorScheme().onSecondaryContainer,
            disabledContainerColor: Color = Platform.colorScheme().surfaceVariant.copy(
                alpha = DisabledBackgroundColorOpacity
            ),
            disabledContentColor: Color = Platform.colorScheme().outline.copy(
                alpha = DisabledContentColorOpacity
            ),
            focusedSelectedContainerColor: Color = Platform.colorScheme().onPrimaryContainer,
            focusedSelectedContentColor: Color = Platform.colorScheme().onPrimary,
            pressedSelectedContainerColor: Color = Platform.colorScheme().secondary,
            pressedSelectedContentColor: Color = Platform.colorScheme().onSecondary,
        ) = PlatformSelectableChipColors(
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