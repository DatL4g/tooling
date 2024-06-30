package dev.datlag.tooling.compose.platform

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
class PlatformBorder(
    val border: BorderStroke,
    val inset: Dp = 0.dp,
    val shape: Shape = GenericShape { _, _ -> close() }
) {
    companion object {
        val None = PlatformBorder(
            border = BorderStroke(width = 0.dp, color = Color.Transparent),
            inset = 0.dp,
            shape = RectangleShape
        )
    }
}