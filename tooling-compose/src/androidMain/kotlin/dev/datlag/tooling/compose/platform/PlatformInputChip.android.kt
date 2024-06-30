package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.FilterChipDefaults
import androidx.tv.material3.InputChipDefaults
import androidx.tv.material3.SelectableChipBorder
import androidx.tv.material3.SelectableChipColors
import androidx.tv.material3.SelectableChipGlow
import androidx.tv.material3.SelectableChipScale
import androidx.tv.material3.SelectableChipShape
import dev.datlag.tooling.Platform
import dev.datlag.tooling.compose.animatedScale
import dev.datlag.tooling.compose.glow
import androidx.tv.material3.InputChip as TvInputChip
import androidx.compose.material3.InputChip as DefaultInputChip

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
actual fun PlatformInputChip(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    leadingIcon: @Composable (() -> Unit)?,
    avatar: @Composable (() -> Unit)?,
    trailingIcon: @Composable (() -> Unit)?,
    shape: PlatformSelectableChipShape,
    colors: PlatformSelectableChipColors,
    scale: PlatformSelectableChipScale,
    border: PlatformSelectableChipBorder,
    glow: PlatformSelectableChipGlow,
    interactionSource: MutableInteractionSource?,
    content: @Composable () -> Unit
) {
    if (Platform.rememberIsTv()) {
        TvInputChip(
            selected = selected,
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            leadingIcon = leadingIcon,
            avatar = avatar,
            trailingIcon = trailingIcon,
            shape = shape.asTvInput(hasAvatar = avatar != null),
            colors = colors.asTvInput(),
            scale = scale.asTvInput(),
            border = border.asTvInput(hasAvatar = avatar != null),
            glow = glow.asTvInput(),
            interactionSource = interactionSource,
            content = content
        )
    } else {
        val source = interactionSource ?: remember { MutableInteractionSource() }
        val targetShape = shape.shape(enabled, selected, source)

        DefaultInputChip(
            selected = selected,
            onClick = onClick,
            label = content,
            modifier = modifier
                .animatedScale(scale.scale(enabled, selected, source), source)
                .glow(targetShape, glow.glow(enabled, selected, source)),
            enabled = enabled,
            leadingIcon = leadingIcon,
            avatar = avatar,
            trailingIcon = trailingIcon,
            shape = targetShape,
            colors = colors.colors(source),
            border = border.borderStrokeOrNull(enabled, selected, source),
            interactionSource = source
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
fun PlatformSelectableChipShape.asTvInput(hasAvatar: Boolean): SelectableChipShape {
    return InputChipDefaults.shape(
        hasAvatar = hasAvatar,
        shape = this.shape,
        focusedShape = this.focusedShape,
        pressedShape = this.pressedShape,
        selectedShape = this.selectedShape,
        disabledShape = this.disabledShape,
        focusedSelectedShape = this.focusedSelectedShape,
        focusedDisabledShape = this.focusedDisabledShape,
        pressedSelectedShape = this.pressedSelectedShape,
        selectedDisabledShape = this.selectedDisabledShape,
        focusedSelectedDisabledShape = this.focusedSelectedDisabledShape
    )
}

@Composable
@OptIn(ExperimentalTvMaterial3Api::class)
fun PlatformSelectableChipColors.asTvInput(): SelectableChipColors {
    return InputChipDefaults.colors(
        containerColor = this.containerColor,
        contentColor = this.contentColor,
        focusedContainerColor = this.focusedContainerColor,
        focusedContentColor = this.focusedContentColor,
        pressedContainerColor = this.pressedContainerColor,
        pressedContentColor = this.pressedContentColor,
        selectedContainerColor = this.selectedContainerColor,
        selectedContentColor = this.selectedContentColor,
        disabledContainerColor = this.disabledContainerColor,
        disabledContentColor = this.disabledContentColor,
        focusedSelectedContainerColor = this.focusedSelectedContainerColor,
        focusedSelectedContentColor = this.focusedSelectedContentColor,
        pressedSelectedContainerColor = this.pressedSelectedContainerColor,
        pressedSelectedContentColor = this.pressedSelectedContentColor,
    )
}

@OptIn(ExperimentalTvMaterial3Api::class)
fun PlatformSelectableChipScale.asTvInput(): SelectableChipScale {
    return InputChipDefaults.scale(
        scale = this.scale,
        focusedScale = this.focusedScale,
        pressedScale = this.pressedScale,
        selectedScale = this.selectedScale,
        disabledScale = this.disabledScale,
        focusedSelectedScale = this.focusedSelectedScale,
        focusedDisabledScale = this.focusedDisabledScale,
        pressedSelectedScale = this.pressedSelectedScale,
        selectedDisabledScale = this.selectedDisabledScale,
        focusedSelectedDisabledScale = this.focusedSelectedDisabledScale
    )
}

@Composable
@OptIn(ExperimentalTvMaterial3Api::class)
fun PlatformSelectableChipBorder.asTvInput(hasAvatar: Boolean): SelectableChipBorder {
    return InputChipDefaults.border(
        hasAvatar = hasAvatar,
        border = border.asTv(),
        focusedBorder = focusedBorder.asTv(),
        pressedBorder = pressedBorder.asTv(),
        selectedBorder = selectedBorder.asTv(),
        disabledBorder = disabledBorder.asTv(),
        focusedSelectedBorder = focusedSelectedBorder.asTv(),
        focusedDisabledBorder = focusedDisabledBorder.asTv(),
        pressedSelectedBorder = pressedSelectedBorder.asTv(),
        selectedDisabledBorder = selectedDisabledBorder.asTv(),
        focusedSelectedDisabledBorder = focusedSelectedDisabledBorder.asTv(),
    )
}

@OptIn(ExperimentalTvMaterial3Api::class)
fun PlatformSelectableChipGlow.asTvInput(): SelectableChipGlow {
    return InputChipDefaults.glow(
        glow = glow.asTv(),
        focusedGlow = focusedGlow.asTv(),
        pressedGlow = pressedGlow.asTv(),
        selectedGlow = selectedGlow.asTv(),
        focusedSelectedGlow = focusedSelectedGlow.asTv(),
        pressedSelectedGlow = pressedSelectedGlow.asTv()
    )
}