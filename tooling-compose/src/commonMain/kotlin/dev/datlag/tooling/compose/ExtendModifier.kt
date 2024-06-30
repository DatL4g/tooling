package dev.datlag.tooling.compose

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import dev.datlag.tooling.compose.platform.PlatformGlow

/**
 * Extension function to apply changes to a [Modifier] if a [predicate] is matched.
 *
 * @param [predicate] which should be matched
 * @param [builder] apply changes to the given [Modifier]
 * @return [Modifier] with or without changes depending on the [predicate]
 */
inline fun Modifier.ifTrue(predicate: Boolean, builder: Modifier.() -> Modifier) = then(if (predicate) builder() else Modifier)

/**
 * Extension function to apply changes to a [Modifier] if a [predicate] is not matched.
 *
 * @param [predicate] which should not be matched
 * @param [builder] apply changes to the given [Modifier]
 * @return [Modifier] with or without changes depending on the [predicate]
 */
inline fun Modifier.ifFalse(predicate: Boolean, builder: Modifier.() -> Modifier) = then(if (!predicate) builder() else Modifier)

/**
 *  Extension function for a [Modifier] to scale a [Composable] (down) on click.
 *
 *  @param [minScale] the minimum scale which is used while clicking.
 *  @return the [Modifier] which contains the scale on click changes.
 */
@Composable
fun Modifier.scaleClick(minScale: Float = 0.9F): Modifier {
    var buttonState by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (buttonState) minScale else 1F)

    return graphicsLayer {
        scaleX = scale
        scaleY = scale
    }.pointerInput(buttonState) {
        awaitPointerEventScope {
            buttonState = if (buttonState) {
                waitForUpOrCancellation()
                false
            } else {
                awaitFirstDown(false)
                true
            }
        }
    }
}

/**
 *  Extension function for a [Modifier] to translate a [Composable] vertically on click.
 *
 *  @param [maxTranslation] the maximum translation which is used while clicking.
 *  @return the [Modifier] which contains the scale on click changes.
 */
@Composable
fun Modifier.translateClick(maxTranslation: Float = 10F): Modifier {
    var buttonState by remember { mutableStateOf(false) }
    val translation by animateFloatAsState(if (buttonState) maxTranslation else 0F)

    return graphicsLayer {
        translationY = translation
    }.pointerInput(buttonState) {
        awaitPointerEventScope {
            buttonState = if (buttonState) {
                waitForUpOrCancellation()
                false
            } else {
                awaitFirstDown(false)
                true
            }
        }
    }
}

/**
 *  Extension function to apply changes to a [Modifier] if the [Composable] is focused.
 *
 *  @param [hoverable] whether hovering is enabled and an indicator for focus.
 *  @param [focusable] whether focusing is enabled
 *  @param [interactionSource] the used [MutableInteractionSource] to indicate if a [Composable] is focused.
 *  @param [builder] apply changes to the given [Modifier].
 *  @return the [Modifier] which contains the changes of [builder] if it is focused.
 */
@Composable
fun Modifier.isFocused(
    hoverable: Boolean = true,
    focusable: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    builder: Modifier.() -> Modifier
): Modifier {
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()

    return this.ifTrue(isHovered || isFocused) {
        builder()
    }.hoverable(
        interactionSource = interactionSource,
        enabled = hoverable
    ).focusable(
        interactionSource = interactionSource,
        enabled = focusable
    )
}

/**
 *  Extension function to get notified if the [Composable] is focused.
 *
 *  @param [hoverable] whether hovering is enabled and an indicator for focus.
 *  @param [focusable] whether focusing is enabled
 *  @param [interactionSource] the used [MutableInteractionSource] to indicate if a [Composable] is focused.
 *  @param [onChanged] called when the focus state changes
 *  @return the [Modifier] which contains the changes of focus indication.
 */
@Composable
fun Modifier.onFocusChanged(
    hoverable: Boolean = true,
    focusable: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onChanged: (Boolean) -> Unit
): Modifier {
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()

    val latestValue = remember(isHovered, isFocused) { isHovered || isFocused }

    LaunchedEffect(latestValue) {
        onChanged(latestValue)
    }

    return this.hoverable(
        interactionSource = interactionSource,
        enabled = hoverable
    ).focusable(
        interactionSource = interactionSource,
        enabled = focusable
    )
}

/**
 *  Extension function for a [Modifier] to scale a [Composable] (up) on focus.
 *
 *  @param scale the scale used while the [Composable] is focused.
 *  @param [hoverable] whether hovering is enabled and an indicator for focus.
 *  @param [focusable] whether focusing is enabled
 *  @param [interactionSource] the used [MutableInteractionSource] to indicate if a [Composable] is focused.
 *  @return the [Modifier] which contains the changes of focus indication.
 */
@Composable
fun Modifier.focusScale(
    scale: Float = 1.1F,
    hoverable: Boolean = true,
    focusable: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
): Modifier {
    return isFocused(
        hoverable,
        focusable,
        interactionSource
    ) {
        graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
    }
}

@Composable
fun Modifier.animatedScale(
    scale: Float,
    interactionSource: MutableInteractionSource
): Modifier {
    val interaction by interactionSource.interactions.collectAsState(
        initial = FocusInteraction.Focus()
    )

    val animationSpec = defaultScaleAnimationSpec(interaction)

    val animatedScale by animateFloatAsState(
        targetValue = scale,
        animationSpec = animationSpec
    )

    return drawWithContent {
        scale(animatedScale) {
            this@drawWithContent.drawContent()
        }
    }
}

private fun defaultScaleAnimationSpec(interaction: Interaction): TweenSpec<Float> = tween(
    durationMillis = when (interaction) {
        is FocusInteraction.Focus -> 300
        is FocusInteraction.Unfocus -> 500
        is PressInteraction.Press -> 120
        is PressInteraction.Release -> 300
        is PressInteraction.Cancel -> 300
        else -> 300
    },
    easing = CubicBezierEasing(0f, 0f, 0.2f, 1f)
)

@Composable
fun Modifier.glow(
    shape: Shape,
    glow: PlatformGlow
): Modifier {
    val color = surfaceColorAtElevation(
        color = glow.elevationColor,
        elevation = glow.elevation
    )

    return shadow(
        elevation = glow.elevation,
        shape = shape,
        ambientColor = color,
        spotColor = color
    )
}

@Composable
internal fun surfaceColorAtElevation(color: Color, elevation: Dp): Color {
    return if (color == MaterialTheme.colorScheme.surface) {
        MaterialTheme.colorScheme.surfaceColorAtElevation(elevation)
    } else {
        color
    }
}