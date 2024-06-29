package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.SwitchColors as DefaultSwitchColors
import androidx.tv.material3.SwitchColors as TvSwitchColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.material3.Switch as DefaultSwitch
import androidx.tv.material3.Switch as TvSwitch
import dev.datlag.tooling.Platform

@Composable
actual fun PlatformSwitch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier,
    thumbContent: @Composable (() -> Unit)?,
    enabled: Boolean,
    colors: DefaultSwitchColors,
    interactionSource: MutableInteractionSource?
) {
    if (Platform.rememberIsTv()) {
        TvSwitch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = modifier,
            thumbContent = thumbContent,
            enabled = enabled,
            colors = colors.asTv(),
            interactionSource = interactionSource
        )
    } else {
        DefaultSwitch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = modifier,
            thumbContent = thumbContent,
            enabled = enabled,
            colors = colors,
            interactionSource = interactionSource ?: remember { MutableInteractionSource() }
        )
    }
}

@Composable
fun PlatformSwitch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier,
    thumbContent: @Composable (() -> Unit)?,
    enabled: Boolean,
    colors: TvSwitchColors,
    interactionSource: MutableInteractionSource?
) {
    if (Platform.rememberIsTv()) {
        TvSwitch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = modifier,
            thumbContent = thumbContent,
            enabled = enabled,
            colors = colors,
            interactionSource = interactionSource
        )
    } else {
        DefaultSwitch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = modifier,
            thumbContent = thumbContent,
            enabled = enabled,
            colors = colors.asDefault(),
            interactionSource = interactionSource ?: remember { MutableInteractionSource() }
        )
    }
}

fun DefaultSwitchColors.asTv(): TvSwitchColors {
    return TvSwitchColors(
        checkedThumbColor = this.checkedThumbColor,
        checkedTrackColor = this.checkedTrackColor,
        checkedBorderColor = this.checkedBorderColor,
        checkedIconColor = this.checkedIconColor,

        uncheckedThumbColor = this.uncheckedThumbColor,
        uncheckedTrackColor = this.uncheckedTrackColor,
        uncheckedBorderColor = this.uncheckedBorderColor,
        uncheckedIconColor = this.uncheckedIconColor,

        disabledCheckedThumbColor = this.disabledCheckedThumbColor,
        disabledCheckedTrackColor = this.disabledCheckedTrackColor,
        disabledCheckedBorderColor = this.disabledCheckedBorderColor,
        disabledCheckedIconColor = this.disabledCheckedIconColor,

        disabledUncheckedThumbColor = this.disabledUncheckedThumbColor,
        disabledUncheckedTrackColor = this.disabledUncheckedTrackColor,
        disabledUncheckedBorderColor = this.disabledUncheckedBorderColor,
        disabledUncheckedIconColor = this.disabledUncheckedIconColor,
    )
}

fun TvSwitchColors.asDefault(): DefaultSwitchColors {
    return DefaultSwitchColors(
        checkedThumbColor = this.checkedThumbColor,
        checkedTrackColor = this.checkedTrackColor,
        checkedBorderColor = this.checkedBorderColor,
        checkedIconColor = this.checkedIconColor,

        uncheckedThumbColor = this.uncheckedThumbColor,
        uncheckedTrackColor = this.uncheckedTrackColor,
        uncheckedBorderColor = this.uncheckedBorderColor,
        uncheckedIconColor = this.uncheckedIconColor,

        disabledCheckedThumbColor = this.disabledCheckedThumbColor,
        disabledCheckedTrackColor = this.disabledCheckedTrackColor,
        disabledCheckedBorderColor = this.disabledCheckedBorderColor,
        disabledCheckedIconColor = this.disabledCheckedIconColor,

        disabledUncheckedThumbColor = this.disabledUncheckedThumbColor,
        disabledUncheckedTrackColor = this.disabledUncheckedTrackColor,
        disabledUncheckedBorderColor = this.disabledUncheckedBorderColor,
        disabledUncheckedIconColor = this.disabledUncheckedIconColor,
    )
}