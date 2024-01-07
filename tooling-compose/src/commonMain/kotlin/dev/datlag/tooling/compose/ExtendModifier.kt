package dev.datlag.tooling.compose

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput

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
fun Modifier.scaleClick(minScale: Float = 0.9F) = composed {
    var buttonState by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (buttonState) minScale else 1F)

    graphicsLayer {
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
fun Modifier.translateClick(maxTranslation: Float = 10F) = composed {
    var buttonState by remember { mutableStateOf(false) }
    val translation by animateFloatAsState(if (buttonState) maxTranslation else 0F)

    graphicsLayer {
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
): Modifier = composed {
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()

    this.ifTrue(isHovered || isFocused) {
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
): Modifier = composed {
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()

    val latestValue = remember(isHovered, isFocused) { isHovered || isFocused }

    LaunchedEffect(latestValue) {
        onChanged(latestValue)
    }

    this.hoverable(
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
) = composed {
    isFocused(
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