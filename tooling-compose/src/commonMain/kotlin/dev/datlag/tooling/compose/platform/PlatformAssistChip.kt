package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ChipColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.datlag.tooling.Platform

@Immutable
class PlatformClickableChipShape(
    val shape: Shape,
    val focusedShape: Shape = shape,
    val pressedShape: Shape = shape,
    val disabledShape: Shape = shape,
    val focusedDisabledShape: Shape
) {
    fun shape(
        enabled: Boolean,
        focused: Boolean,
        pressed: Boolean
    ): Shape {
        return when {
            enabled && pressed -> pressedShape
            enabled && focused -> focusedShape
            enabled -> shape
            !enabled && focused -> focusedDisabledShape
            !enabled -> disabledShape
            else -> disabledShape
        }
    }

    @Composable
    fun shape(
        enabled: Boolean,
        interactionSource: MutableInteractionSource
    ): Shape {
        val focused by interactionSource.collectIsFocusedAsState()
        val pressed by interactionSource.collectIsPressedAsState()

        return shape(
            enabled = enabled,
            focused = focused,
            pressed = pressed
        )
    }

    companion object {
        @Composable
        fun assist(
            shape: Shape = Platform.shapes().small,
            focusedShape: Shape = shape,
            pressedShape: Shape = shape,
            disabledShape: Shape = shape,
            focusedDisabledShape: Shape = disabledShape
        ) = PlatformClickableChipShape(
            shape = shape,
            focusedShape = focusedShape,
            pressedShape = pressedShape,
            disabledShape = disabledShape,
            focusedDisabledShape = focusedDisabledShape
        )
    }
}

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
    fun colors(
        focused: Boolean,
        pressed: Boolean
    ): ChipColors {
        val (container, content) = when {
            focused -> focusedContainerColor to focusedContentColor
            pressed -> pressedContainerColor to pressedContentColor
            else -> containerColor to contentColor
        }

        return ChipColors(
            containerColor = container,
            labelColor = content,
            leadingIconContentColor = content,
            trailingIconContentColor = content,
            disabledContainerColor = this.disabledContainerColor,
            disabledLabelColor = this.disabledContentColor,
            disabledLeadingIconContentColor = this.disabledContentColor,
            disabledTrailingIconContentColor = this.disabledContentColor
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

        private const val DisabledAssistBackgroundColorOpacity = 0.2f
        private const val DisabledAssistContentColorOpacity = 0.8f

        @Composable
        fun assist(
            containerColor: Color = Color.Transparent,
            contentColor: Color = Platform.colorScheme().onSurfaceVariant,
            focusedContainerColor: Color = Platform.colorScheme().onSurface,
            focusedContentColor: Color = Platform.colorScheme().inverseOnSurface,
            pressedContainerColor: Color = Platform.colorScheme().onSurfaceVariant,
            pressedContentColor: Color = Platform.colorScheme().surface,
            disabledContainerColor: Color = Platform.colorScheme().surfaceVariant.copy(
                alpha = DisabledAssistBackgroundColorOpacity
            ),
            disabledContentColor: Color = Platform.colorScheme().outline.copy(
                alpha = DisabledAssistContentColorOpacity
            )
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
    }
}

@Immutable
class PlatformClickableChipBorder(
    val border: PlatformBorder,
    val focusedBorder: PlatformBorder,
    val pressedBorder: PlatformBorder,
    val disabledBorder: PlatformBorder,
    val focusedDisabledBorder: PlatformBorder
) {
    fun borderStroke(
        enabled: Boolean,
        focused: Boolean,
        pressed: Boolean
    ): BorderStroke {
        return when {
            enabled && pressed -> pressedBorder.border
            enabled && focused -> focusedBorder.border
            enabled -> border.border
            !enabled && focused -> focusedDisabledBorder.border
            !enabled -> disabledBorder.border
            else -> disabledBorder.border
        }
    }

    fun borderStrokeOrNull(
        enabled: Boolean,
        focused: Boolean,
        pressed: Boolean
    ): BorderStroke? {
        val border = borderStroke(enabled, focused, pressed)

        return if (border.width <= 0.dp) {
            null
        } else {
            border
        }
    }

    @Composable
    fun borderStroke(
        enabled: Boolean,
        interactionSource: MutableInteractionSource
    ): BorderStroke {
        val focused by interactionSource.collectIsFocusedAsState()
        val pressed by interactionSource.collectIsPressedAsState()

        return borderStroke(
            enabled = enabled,
            focused = focused,
            pressed = pressed
        )
    }

    @Composable
    fun borderStrokeOrNull(
        enabled: Boolean,
        interactionSource: MutableInteractionSource
    ): BorderStroke? {
        val focused by interactionSource.collectIsFocusedAsState()
        val pressed by interactionSource.collectIsPressedAsState()

        return borderStrokeOrNull(
            enabled = enabled,
            focused = focused,
            pressed = pressed
        )
    }

    companion object {
        @Composable
        fun assist(
            border: PlatformBorder = PlatformBorder(
                border = BorderStroke(
                    width = 1.dp,
                    color = Platform.colorScheme().outline
                ),
                shape = Platform.shapes().small
            ),
            focusedBorder: PlatformBorder = PlatformBorder.None,
            pressedBorder: PlatformBorder = focusedBorder,
            disabledBorder: PlatformBorder = PlatformBorder(
                border = BorderStroke(
                    width = 1.dp,
                    color = Platform.colorScheme().surfaceVariant
                ),
                shape = Platform.shapes().small
            ),
            focusedDisabledBorder: PlatformBorder = border
        ) = PlatformClickableChipBorder(
            border = border,
            focusedBorder = focusedBorder,
            pressedBorder = pressedBorder,
            disabledBorder = disabledBorder,
            focusedDisabledBorder = focusedDisabledBorder
        )
    }
}

@Immutable
class PlatformBorder(
    val border: BorderStroke,
    val inset: Dp = 0.dp,
    val shape: Shape = GenericShape { _, _ -> close() }
) {
    companion object {
        val None = PlatformBorder(
            border = BorderStroke(width = 0.dp, color = Color.Transparent),
            inset = 0.dp,
            shape = RectangleShape
        )
    }
}

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
    }
}

@Immutable
class PlatformGlow(
    val elevationColor: Color,
    val elevation: Dp
) {
    companion object {
        val None = PlatformGlow(
            elevationColor = Color.Transparent,
            elevation = 0.dp
        )
    }
}

@Composable
expect fun PlatformAssistChip(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    shape: PlatformClickableChipShape = PlatformClickableChipShape.assist(),
    colors: PlatformClickableChipColors = PlatformClickableChipColors.assist(),
    scale: PlatformClickableChipScale = PlatformClickableChipScale.assist(),
    border: PlatformClickableChipBorder = PlatformClickableChipBorder.assist(),
    glow: PlatformClickableChipGlow = PlatformClickableChipGlow.assist(),
    interactionSource: MutableInteractionSource? = null,
    content: @Composable () -> Unit
)