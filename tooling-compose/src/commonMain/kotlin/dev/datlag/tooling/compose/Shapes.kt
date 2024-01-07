package dev.datlag.tooling.compose

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Shape which leaves top start and bottom end corner unchanged, and applies a specified [Dp] on other corners.
 *
 * @param [baseShape] the base [CornerBasedShape] which will be used.
 * @param [otherCorner] the changes in [Dp] on the other corners.
 * @return a [CornerBasedShape] which is changed on all other corners.
 */
fun TopStartBottomEndCornerShape(baseShape: CornerBasedShape = CircleShape, otherCorner: Dp = 2.dp) = baseShape.copy(
    topEnd = CornerSize(otherCorner),
    bottomStart = CornerSize(otherCorner)
)

/**
 * Shape which leaves top end and bottom start corner unchanged, and applies a specified [Dp] on other corners.
 *
 * @param [baseShape] the base [CornerBasedShape] which will be used.
 * @param [otherCorner] the changes in [Dp] on the other corners.
 * @return a [CornerBasedShape] which is changed on all other corners.
 */
fun TopEndBottomStartCornerShape(baseShape: CornerBasedShape = CircleShape, otherCorner: Dp = 2.dp) = baseShape.copy(
    topStart = CornerSize(otherCorner),
    bottomEnd = CornerSize(otherCorner)
)

/**
 * Shape which leaves top start corner unchanged, and applies a specified [Dp] on other corners.
 *
 * @param [baseShape] the base [CornerBasedShape] which will be used.
 * @param [otherCorner] the changes in [Dp] on the other corners.
 * @return a [CornerBasedShape] which is changed on all other corners.
 */
fun TopStartCornerShape(baseShape: CornerBasedShape = CircleShape, otherCorner: Dp = 2.dp) = baseShape.copy(
    topEnd = CornerSize(otherCorner),
    bottomStart = CornerSize(otherCorner),
    bottomEnd = CornerSize(otherCorner),
)

/**
 * Shape which leaves top end corner unchanged, and applies a specified [Dp] on other corners.
 *
 * @param [baseShape] the base [CornerBasedShape] which will be used.
 * @param [otherCorner] the changes in [Dp] on the other corners.
 * @return a [CornerBasedShape] which is changed on all other corners.
 */
fun TopEndCornerShape(baseShape: CornerBasedShape = CircleShape, otherCorner: Dp = 2.dp) = baseShape.copy(
    topStart = CornerSize(otherCorner),
    bottomStart = CornerSize(otherCorner),
    bottomEnd = CornerSize(otherCorner),
)

/**
 * Shape which leaves bottom start corner unchanged, and applies a specified [Dp] on other corners.
 *
 * @param [baseShape] the base [CornerBasedShape] which will be used.
 * @param [otherCorner] the changes in [Dp] on the other corners.
 * @return a [CornerBasedShape] which is changed on all other corners.
 */
fun BottomStartCornerShape(baseShape: CornerBasedShape = CircleShape, otherCorner: Dp = 2.dp) = baseShape.copy(
    topStart = CornerSize(otherCorner),
    topEnd = CornerSize(otherCorner),
    bottomEnd = CornerSize(otherCorner),
)


/**
 * Shape which leaves bottom end corner unchanged, and applies a specified [Dp] on other corners.
 *
 * @param [baseShape] the base [CornerBasedShape] which will be used.
 * @param [otherCorner] the changes in [Dp] on the other corners.
 * @return a [CornerBasedShape] which is changed on all other corners.
 */
fun BottomEndCornerShape(baseShape: CornerBasedShape = CircleShape, otherCorner: Dp = 2.dp) = baseShape.copy(
    topStart = CornerSize(otherCorner),
    topEnd = CornerSize(otherCorner),
    bottomStart = CornerSize(otherCorner),
)

/**
 * Shape which leaves top start and bottom start corner unchanged, and applies a specified [Dp] on other corners.
 *
 * @param [baseShape] the base [CornerBasedShape] which will be used.
 * @param [otherCorner] the changes in [Dp] on the other corners.
 * @return a [CornerBasedShape] which is changed on all other corners.
 */
fun StartCornerShape(baseShape: CornerBasedShape = CircleShape, otherCorner: Dp = 2.dp) = baseShape.copy(
    topEnd = CornerSize(otherCorner),
    bottomEnd = CornerSize(otherCorner)
)

/**
 * Shape which leaves top end and bottom end corner unchanged, and applies a specified [Dp] on other corners.
 *
 * @param [baseShape] the base [CornerBasedShape] which will be used.
 * @param [otherCorner] the changes in [Dp] on the other corners.
 * @return a [CornerBasedShape] which is changed on all other corners.
 */
fun EndCornerShape(baseShape: CornerBasedShape = CircleShape, otherCorner: Dp = 2.dp) = baseShape.copy(
    topStart = CornerSize(otherCorner),
    bottomStart = CornerSize(otherCorner)
)

/**
 * Shape which leaves top start and top end corner unchanged, and applies a specified [Dp] on other corners.
 *
 * @param [baseShape] the base [CornerBasedShape] which will be used.
 * @param [otherCorner] the changes in [Dp] on the other corners.
 * @return a [CornerBasedShape] which is changed on all other corners.
 */
fun TopCornerShape(baseShape: CornerBasedShape = CircleShape, otherCorner: Dp = 2.dp) = baseShape.copy(
    bottomStart = CornerSize(otherCorner),
    bottomEnd = CornerSize(otherCorner)
)


/**
 * Shape which leaves bottom start and bottom end corner unchanged, and applies a specified [Dp] on other corners.
 *
 * @param [baseShape] the base [CornerBasedShape] which will be used.
 * @param [otherCorner] the changes in [Dp] on the other corners.
 * @return a [CornerBasedShape] which is changed on all other corners.
 */
fun BottomCornerShape(baseShape: CornerBasedShape = CircleShape, otherCorner: Dp = 2.dp) = baseShape.copy(
    topStart = CornerSize(otherCorner),
    topEnd = CornerSize(otherCorner)
)

/**
 * Shape which applies a specified [Dp] on all corners.
 * Useful as middle part for StartCornerShape and EndCornerShape for example.
 *
 * @param [baseShape] the base [CornerBasedShape] which will be used.
 * @param [cornerSize] the changes in [Dp] on the corners.
 * @return a [CornerBasedShape] which is changed on all corners.
 */
fun MiddleCornerShape(baseShape: CornerBasedShape = CircleShape, cornerSize: Dp = 2.dp) = baseShape.copy(
    topStart = CornerSize(cornerSize),
    topEnd = CornerSize(cornerSize),
    bottomStart = CornerSize(cornerSize),
    bottomEnd = CornerSize(cornerSize),
)