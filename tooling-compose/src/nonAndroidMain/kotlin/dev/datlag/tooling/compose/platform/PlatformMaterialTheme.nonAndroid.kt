package dev.datlag.tooling.compose.platform

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable

@Composable
actual fun PlatformMaterialTheme(
    colorScheme: ColorScheme,
    shapes: Shapes,
    typography: Typography,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = colorScheme,
        shapes = shapes,
        typography = typography,
        content = content
    )
}

@Composable
actual fun CombinedPlatformMaterialTheme(
    colorScheme: ColorScheme,
    shapes: Shapes,
    typography: Typography,
    content: @Composable () -> Unit
) = PlatformMaterialTheme(
    colorScheme = colorScheme,
    shapes = shapes,
    typography = typography,
    content = content
)