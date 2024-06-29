package dev.datlag.tooling.compose.platform

import androidx.compose.material3.Typography as DefaultTypography
import androidx.tv.material3.Typography as TvTypography
import androidx.compose.material3.ColorScheme as DefaultColorScheme
import androidx.tv.material3.ColorScheme as TvColorScheme
import androidx.compose.material3.Shapes as DefaultShapes
import androidx.tv.material3.Shapes as TvShapes
import androidx.compose.runtime.Composable
import androidx.compose.material3.MaterialTheme as DefaultTheme
import androidx.tv.material3.MaterialTheme as TvTheme
import dev.datlag.tooling.Platform

@Composable
actual fun PlatformMaterialTheme(
    colorScheme: DefaultColorScheme,
    shapes: DefaultShapes,
    typography: DefaultTypography,
    content: @Composable () -> Unit
) {
    if (Platform.rememberIsTv()) {
        TvTheme(
            colorScheme = colorScheme.asTv(),
            shapes = shapes.asTv(),
            typography = typography.asTv(),
            content = content
        )
    } else {
        DefaultTheme(
            colorScheme = colorScheme,
            shapes = shapes,
            typography = typography,
            content = content
        )
    }
}

@Composable
fun PlatformMaterialTheme(
    colorScheme: TvColorScheme,
    shapes: TvShapes,
    typography: TvTypography,
    content: @Composable () -> Unit
) {
    if (Platform.rememberIsTv()) {
        TvTheme(
            colorScheme = colorScheme,
            shapes = shapes,
            typography = typography,
            content = content
        )
    } else {
        DefaultTheme(
            colorScheme = colorScheme.asDefault(),
            shapes = shapes.asDefault(),
            typography = typography.asDefault(),
            content = content
        )
    }
}

@Composable
actual fun CombinedPlatformMaterialTheme(
    colorScheme: DefaultColorScheme,
    shapes: DefaultShapes,
    typography: DefaultTypography,
    content: @Composable () -> Unit
) {
    DefaultTheme(
        colorScheme = colorScheme,
        shapes = shapes,
        typography = typography
    ) {
        TvTheme(
            colorScheme = colorScheme.asTv(),
            shapes = shapes.asTv(),
            typography = typography.asTv(),
            content = content
        )
    }
}

@Composable
fun CombinedPlatformMaterialTheme(
    colorScheme: TvColorScheme,
    shapes: TvShapes,
    typography: TvTypography,
    content: @Composable () -> Unit
) {
    DefaultTheme(
        colorScheme = colorScheme.asDefault(),
        shapes = shapes.asDefault(),
        typography = typography.asDefault()
    ) {
        TvTheme(
            colorScheme = colorScheme,
            shapes = shapes,
            typography = typography,
            content = content
        )
    }
}