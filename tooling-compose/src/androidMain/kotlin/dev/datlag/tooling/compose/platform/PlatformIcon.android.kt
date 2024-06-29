package dev.datlag.tooling.compose.platform

import androidx.compose.material3.Icon as DefaultIcon
import androidx.tv.material3.Icon as TvIcon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import dev.datlag.tooling.Platform

@Composable
actual fun PlatformIcon(
    bitmap: ImageBitmap,
    contentDescription: String?,
    modifier: Modifier,
    tint: Color
) {
    if (Platform.rememberIsTv()) {
        TvIcon(
            bitmap = bitmap,
            contentDescription = contentDescription,
            modifier = modifier,
            tint = tint
        )
    } else {
        DefaultIcon(
            bitmap = bitmap,
            contentDescription = contentDescription,
            modifier = modifier,
            tint = tint
        )
    }
}

@Composable
actual fun PlatformIcon(
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier,
    tint: Color
) {
    if (Platform.rememberIsTv()) {
        TvIcon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = modifier,
            tint = tint
        )
    } else {
        DefaultIcon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = modifier,
            tint = tint
        )
    }
}

@Composable
actual fun PlatformIcon(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier,
    tint: Color
) {
    if (Platform.rememberIsTv()) {
        TvIcon(
            painter = painter,
            contentDescription = contentDescription,
            modifier = modifier,
            tint = tint
        )
    } else {
        DefaultIcon(
            painter = painter,
            contentDescription = contentDescription,
            modifier = modifier,
            tint = tint
        )
    }
}