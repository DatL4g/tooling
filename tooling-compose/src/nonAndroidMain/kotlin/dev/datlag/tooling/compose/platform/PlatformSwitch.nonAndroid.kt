package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
actual fun PlatformSwitch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier,
    thumbContent: @Composable (() -> Unit)?,
    enabled: Boolean,
    colors: SwitchColors,
    interactionSource: MutableInteractionSource?
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        thumbContent = thumbContent,
        enabled = enabled,
        colors = colors,
        interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    )
}