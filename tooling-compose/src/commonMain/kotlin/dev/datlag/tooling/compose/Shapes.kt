package dev.datlag.tooling.compose

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun TopStartBottomEndCornerShape(baseShape: CornerBasedShape = CircleShape, otherCorner: Dp = 2.dp) = baseShape.copy(
    topEnd = CornerSize(otherCorner),
    bottomStart = CornerSize(otherCorner)
)

fun TopEndBottomStartCornerShape(baseShape: CornerBasedShape = CircleShape, otherCorner: Dp = 2.dp) = baseShape.copy(
    topStart = CornerSize(otherCorner),
    bottomEnd = CornerSize(otherCorner)
)

fun TopStartCornerShape(baseShape: CornerBasedShape = CircleShape, otherCorner: Dp = 2.dp) = baseShape.copy(
    topEnd = CornerSize(otherCorner),
    bottomStart = CornerSize(otherCorner),
    bottomEnd = CornerSize(otherCorner),
)

fun TopEndCornerShape(baseShape: CornerBasedShape = CircleShape, otherCorner: Dp = 2.dp) = baseShape.copy(
    topStart = CornerSize(otherCorner),
    bottomStart = CornerSize(otherCorner),
    bottomEnd = CornerSize(otherCorner),
)

fun BottomStartCornerShape(baseShape: CornerBasedShape = CircleShape, otherCorner: Dp = 2.dp) = baseShape.copy(
    topStart = CornerSize(otherCorner),
    topEnd = CornerSize(otherCorner),
    bottomEnd = CornerSize(otherCorner),
)

fun BottomEndCornerShape(baseShape: CornerBasedShape = CircleShape, otherCorner: Dp = 2.dp) = baseShape.copy(
    topStart = CornerSize(otherCorner),
    topEnd = CornerSize(otherCorner),
    bottomStart = CornerSize(otherCorner),
)

fun StartCornerShape(baseShape: CornerBasedShape = CircleShape, otherCorner: Dp = 2.dp) = baseShape.copy(
    topEnd = CornerSize(otherCorner),
    bottomEnd = CornerSize(otherCorner)
)

fun EndCornerShape(baseShape: CornerBasedShape = CircleShape, otherCorner: Dp = 2.dp) = baseShape.copy(
    topStart = CornerSize(otherCorner),
    bottomStart = CornerSize(otherCorner)
)

fun TopCornerShape(baseShape: CornerBasedShape = CircleShape, otherCorner: Dp = 2.dp) = baseShape.copy(
    bottomStart = CornerSize(otherCorner),
    bottomEnd = CornerSize(otherCorner)
)

fun BottomCornerShape(baseShape: CornerBasedShape = CircleShape, otherCorner: Dp = 2.dp) = baseShape.copy(
    topStart = CornerSize(otherCorner),
    topEnd = CornerSize(otherCorner)
)

fun MiddleCornerShape(baseShape: CornerBasedShape = CircleShape, cornerSize: Dp = 2.dp) = baseShape.copy(
    topStart = CornerSize(cornerSize),
    topEnd = CornerSize(cornerSize),
    bottomStart = CornerSize(cornerSize),
    bottomEnd = CornerSize(cornerSize),
)