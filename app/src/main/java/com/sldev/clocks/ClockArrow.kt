package com.sldev.clocks

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.dp

@Composable
fun ClockArrow(
    modifier: Modifier = Modifier,
    rectangleWidth: Float = 100f,
    rectangleHeight: Float = rectangleWidth / 5f,
    rotationAngle: Float,
    color: Color = Color.LightGray
) {
    val angle by animateFloatAsState(
        targetValue = rotationAngle,
        animationSpec = tween(durationMillis = 500)
    )
    Canvas(
        modifier = modifier
    ) {
        rotate(angle) {
            translate(rectangleWidth / 2f, 0f) {
                drawRect(
                    color = color,
                    topLeft = Offset(-rectangleWidth / 2, -rectangleHeight / 2),
                    size = Size(rectangleWidth, rectangleHeight),
                )
            }
        }
    }
}