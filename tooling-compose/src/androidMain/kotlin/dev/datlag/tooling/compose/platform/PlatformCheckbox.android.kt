package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.CheckboxColors as DefaultCheckboxColors
import androidx.tv.material3.CheckboxColors as TvCheckboxColors
import androidx.compose.material3.Checkbox as DefaultCheckbox
import androidx.tv.material3.Checkbox as TvCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import dev.datlag.tooling.Platform

@Composable
actual fun PlatformCheckbox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier,
    enabled: Boolean,
    colors: DefaultCheckboxColors,
    interactionSource: MutableInteractionSource?
) {
    if (Platform.rememberIsTv()) {
        TvCheckbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = modifier,
            enabled = enabled,
            colors = colors.asTv(),
            interactionSource = interactionSource
        )
    } else {
        DefaultCheckbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = modifier,
            enabled = enabled,
            colors = colors,
            interactionSource = interactionSource ?: remember { MutableInteractionSource() }
        )
    }
}

@Composable
fun PlatformCheckbox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier,
    enabled: Boolean,
    colors: TvCheckboxColors,
    interactionSource: MutableInteractionSource?
) {
    if (Platform.rememberIsTv()) {
        TvCheckbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = modifier,
            enabled = enabled,
            colors = colors,
            interactionSource = interactionSource
        )
    } else {
        DefaultCheckbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = modifier,
            enabled = enabled,
            colors = colors.asDefault(),
            interactionSource = interactionSource ?: remember { MutableInteractionSource() }
        )
    }
}

fun DefaultCheckboxColors.asTv(): TvCheckboxColors {
    return TvCheckboxColors(
        checkedCheckmarkColor = this.checkedCheckmarkColor,
        uncheckedCheckmarkColor = this.uncheckedCheckmarkColor,
        checkedBoxColor = this.checkedBoxColor,
        uncheckedBoxColor = this.uncheckedBoxColor,
        disabledCheckedBoxColor = this.disabledCheckedBoxColor,
        disabledUncheckedBoxColor = this.disabledUncheckedBoxColor,
        disabledIndeterminateBoxColor = this.disabledIndeterminateBoxColor,
        checkedBorderColor = this.checkedBorderColor,
        uncheckedBorderColor = this.uncheckedBorderColor,
        disabledBorderColor = this.disabledBorderColor,
        disabledUncheckedBorderColor = this.disabledUncheckedBorderColor,
        disabledIndeterminateBorderColor = this.disabledIndeterminateBorderColor
    )
}

fun TvCheckboxColors.asDefault(): DefaultCheckboxColors {
    return DefaultCheckboxColors(
        checkedCheckmarkColor = this.checkedCheckmarkColor,
        uncheckedCheckmarkColor = this.uncheckedCheckmarkColor,
        checkedBoxColor = this.checkedBoxColor,
        uncheckedBoxColor = this.uncheckedBoxColor,
        disabledCheckedBoxColor = this.disabledCheckedBoxColor,
        disabledUncheckedBoxColor = this.disabledUncheckedBoxColor,
        disabledIndeterminateBoxColor = this.disabledIndeterminateBoxColor,
        checkedBorderColor = this.checkedBorderColor,
        uncheckedBorderColor = this.uncheckedBorderColor,
        disabledBorderColor = this.disabledBorderColor,
        disabledUncheckedBorderColor = this.disabledUncheckedBorderColor,
        disabledIndeterminateBorderColor = this.disabledIndeterminateBorderColor
    )
}