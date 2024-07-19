package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import dev.datlag.tooling.Platform

@Composable
actual fun Platform.rememberIsTv(): Boolean {
    return remember { isTVOS }
}

@Composable
actual fun Platform.localContentColor(): Color {
    return LocalContentColor.current
}

@Composable
actual fun Platform.localTextStyle(): TextStyle {
    return LocalTextStyle.current
}

@Composable
actual fun Platform.colorScheme(): ColorScheme {
    return MaterialTheme.colorScheme
}

@Composable
actual fun Platform.shapes(): Shapes {
    return MaterialTheme.shapes
}

@Composable
actual fun Platform.typography(): Typography {
    return MaterialTheme.typography
}

@Composable
actual fun PlatformProvideTextStyle(
    value: TextStyle,
    content: @Composable () -> Unit
) {
    ProvideTextStyle(value, content)
}

@Composable
actual fun CombinedPlatformProvideTextStyle(
    value: TextStyle,
    content: @Composable () -> Unit
) = PlatformProvideTextStyle(value, content)

@Composable
actual fun Platform.buttonPadding(): PaddingValues {
    return ButtonDefaults.ContentPadding
}

@Composable
actual fun CombinedPlatformProvideContentColor(
    value: Color,
    content: @Composable () -> Unit
) = CompositionLocalProvider(
    LocalContentColor provides value,
    content = content
)