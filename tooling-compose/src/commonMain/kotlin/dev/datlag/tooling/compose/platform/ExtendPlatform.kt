package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import dev.datlag.tooling.Platform

@Composable
expect fun Platform.rememberIsTv(): Boolean

@Composable
expect fun Platform.localContentColor(): Color

@Composable
expect fun Platform.localTextStyle(): TextStyle

@Composable
expect fun Platform.colorScheme(): ColorScheme

@Composable
expect fun Platform.shapes(): Shapes

@Composable
expect fun Platform.typography(): Typography

@Composable
expect fun Platform.buttonPadding(): PaddingValues

@Composable
expect fun PlatformProvideTextStyle(value: TextStyle, content: @Composable () -> Unit)

@Composable
expect fun CombinedPlatformProvideTextStyle(value: TextStyle, content: @Composable () -> Unit)

@Composable
fun ProvideNonTvTextStyle(
    value: TextStyle = LocalTextStyle.current,
    content: @Composable () -> Unit
) = CombinedPlatformProvideTextStyle(value, content)