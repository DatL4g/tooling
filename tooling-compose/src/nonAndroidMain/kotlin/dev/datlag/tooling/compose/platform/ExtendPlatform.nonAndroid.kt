package dev.datlag.tooling.compose.platform

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
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