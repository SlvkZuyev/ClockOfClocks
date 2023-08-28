package com.sldev.clocks

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


class LittleClockElementState(
    angle1: Float = 0f,
    angle2: Float = 0f
) {
    var angle1 by mutableStateOf(angle1)
    var angle2 by mutableStateOf(angle2)
}

@Composable
fun LittleClockElement(
    modifier: Modifier = Modifier,
    state: LittleClockElementState,
    arrowLength: Dp,
) {
    val pxValue = with(LocalDensity.current) { arrowLength.toPx() }
    Box(
        modifier = modifier
            .width(arrowLength * 2)
            .height(arrowLength * 2),
        contentAlignment = Alignment.Center
    ) {
        ClockArrow(
            rotationAngle = state.angle1,
            rectangleWidth = pxValue
        )
        ClockArrow(
            rotationAngle = state.angle2,
            rectangleWidth = pxValue
        )
    }
}