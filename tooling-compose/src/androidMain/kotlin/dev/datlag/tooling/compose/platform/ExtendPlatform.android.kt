package dev.datlag.tooling.compose.platform

import androidx.compose.material3.Typography as DefaultTypography
import androidx.tv.material3.Typography as TvTypography
import androidx.compose.material3.ColorScheme as DefaultColorScheme
import androidx.tv.material3.ColorScheme as TvColorScheme
import androidx.compose.material3.Shapes as DefaultShapes
import androidx.tv.material3.Shapes as TvShapes
import androidx.compose.material3.LocalContentColor as DefaultContentColor
import androidx.tv.material3.LocalContentColor as TvContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.material3.LocalTextStyle as DefaultTextStyle
import androidx.tv.material3.LocalTextStyle as TvTextStyle
import androidx.compose.material3.MaterialTheme as DefaultTheme
import androidx.tv.material3.MaterialTheme as TvTheme
import dev.datlag.tooling.Platform

@Composable
actual fun Platform.rememberIsTv(): Boolean {
    val context = LocalContext.current
    return remember(context) { isTelevision(context) }
}

@Composable
actual fun Platform.localContentColor(): Color {
    return if (rememberIsTv()) {
        TvContentColor.current
    } else {
        DefaultContentColor.current
    }
}

@Composable
actual fun Platform.localTextStyle(): TextStyle {
    return if (rememberIsTv()) {
        TvTextStyle.current
    } else {
        DefaultTextStyle.current
    }
}

@Composable
actual fun Platform.colorScheme(): DefaultColorScheme {
    return if (rememberIsTv()) {
        TvTheme.colorScheme.asDefault()
    } else {
        DefaultTheme.colorScheme
    }
}

@Composable
actual fun Platform.shapes(): DefaultShapes {
    return if (rememberIsTv()) {
        TvTheme.shapes.asDefault()
    } else {
        DefaultTheme.shapes
    }
}

@Composable
actual fun Platform.typography(): DefaultTypography {
    return if (rememberIsTv()) {
        TvTheme.typography.asDefault()
    } else {
        DefaultTheme.typography
    }
}

fun TvColorScheme.asDefault(): DefaultColorScheme {
    return DefaultColorScheme(
        primary = this.primary,
        onPrimary = this.onPrimary,
        primaryContainer = this.primaryContainer,
        onPrimaryContainer = this.onPrimaryContainer,
        inversePrimary = this.inversePrimary,
        secondary = this.secondary,
        onSecondary = this.onSecondary,
        secondaryContainer = this.secondaryContainer,
        onSecondaryContainer = this.onSecondaryContainer,
        tertiary = this.tertiary,
        onTertiary = this.onTertiary,
        tertiaryContainer = this.tertiaryContainer,
        onTertiaryContainer = this.onTertiaryContainer,
        background = this.background,
        onBackground = this.onBackground,
        surface = this.surface,
        onSurface = this.onSurface,
        surfaceVariant = this.surfaceVariant,
        onSurfaceVariant = this.onSurfaceVariant,
        surfaceTint = this.surfaceTint,
        inverseSurface = this.inverseSurface,
        inverseOnSurface = this.inverseOnSurface,
        error = this.error,
        onError = this.onError,
        errorContainer = this.errorContainer,
        onErrorContainer = this.onErrorContainer,
        outline = this.border,
        outlineVariant = this.borderVariant,
        scrim = this.scrim
    )
}

fun DefaultColorScheme.asTv(): TvColorScheme {
    return TvColorScheme(
        primary = this.primary,
        onPrimary = this.onPrimary,
        primaryContainer = this.primaryContainer,
        onPrimaryContainer = this.onPrimaryContainer,
        inversePrimary = this.inversePrimary,
        secondary = this.secondary,
        onSecondary = this.onSecondary,
        secondaryContainer = this.secondaryContainer,
        onSecondaryContainer = this.onSecondaryContainer,
        tertiary = this.tertiary,
        onTertiary = this.onTertiary,
        tertiaryContainer = this.tertiaryContainer,
        onTertiaryContainer = this.onTertiaryContainer,
        background = this.background,
        onBackground = this.onBackground,
        surface = this.surface,
        onSurface = this.onSurface,
        surfaceVariant = this.surfaceVariant,
        onSurfaceVariant = this.onSurfaceVariant,
        surfaceTint = this.surfaceTint,
        inverseSurface = this.inverseSurface,
        inverseOnSurface = this.inverseOnSurface,
        error = this.error,
        onError = this.onError,
        errorContainer = this.errorContainer,
        onErrorContainer = this.onErrorContainer,
        border = this.outline,
        borderVariant = this.outlineVariant,
        scrim = this.scrim
    )
}

fun TvShapes.asDefault(): DefaultShapes {
    return DefaultShapes(
        extraSmall = this.extraSmall,
        small = this.small,
        medium = this.medium,
        large = this.large,
        extraLarge = this.extraLarge
    )
}

fun DefaultShapes.asTv(): TvShapes {
    return TvShapes(
        extraSmall = this.extraSmall,
        small = this.small,
        medium = this.medium,
        large = this.large,
        extraLarge = this.extraLarge
    )
}

fun TvTypography.asDefault(): DefaultTypography {
    return DefaultTypography(
        displayLarge = this.displayLarge,
        displayMedium = this.displayMedium,
        displaySmall = this.displaySmall,
        headlineLarge = this.headlineLarge,
        headlineMedium = this.headlineMedium,
        headlineSmall = this.headlineSmall,
        titleLarge = this.titleLarge,
        titleMedium = this.titleMedium,
        titleSmall = this.titleSmall,
        bodyLarge = this.bodyLarge,
        bodyMedium = this.bodyMedium,
        bodySmall = this.bodySmall,
        labelLarge = this.labelLarge,
        labelMedium = this.labelMedium,
        labelSmall = this.labelSmall
    )
}

fun DefaultTypography.asTv(): TvTypography {
    return TvTypography(
        displayLarge = this.displayLarge,
        displayMedium = this.displayMedium,
        displaySmall = this.displaySmall,
        headlineLarge = this.headlineLarge,
        headlineMedium = this.headlineMedium,
        headlineSmall = this.headlineSmall,
        titleLarge = this.titleLarge,
        titleMedium = this.titleMedium,
        titleSmall = this.titleSmall,
        bodyLarge = this.bodyLarge,
        bodyMedium = this.bodyMedium,
        bodySmall = this.bodySmall,
        labelLarge = this.labelLarge,
        labelMedium = this.labelMedium,
        labelSmall = this.labelSmall
    )
}