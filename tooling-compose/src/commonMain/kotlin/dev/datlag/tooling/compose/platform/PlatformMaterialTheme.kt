package dev.datlag.tooling.compose.platform

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import dev.datlag.tooling.Platform

@Composable
expect fun PlatformMaterialTheme(
    colorScheme: ColorScheme = Platform.colorScheme(),
    shapes: Shapes = Platform.shapes(),
    typography: Typography = Platform.typography(),
    content: @Composable () -> Unit
)

@Composable
expect fun CombinedPlatformMaterialTheme(
    colorScheme: ColorScheme = Platform.colorScheme(),
    shapes: Shapes = Platform.shapes(),
    typography: Typography = Platform.typography(),
    content: @Composable () -> Unit
)