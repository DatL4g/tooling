package dev.datlag.tooling.compose.platform

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
class PlatformGlow(
    val elevationColor: Color,
    val elevation: Dp
) {
    companion object {
        val None = PlatformGlow(
            elevationColor = Color.Transparent,
            elevation = 0.dp
        )
    }
}