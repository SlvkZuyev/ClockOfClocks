package com.sldev.clocks

import android.util.Log
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sldev.clocks.clock_numbers_states.SegmentType
import com.sldev.clocks.clock_numbers_states.getLittleClockStates
import kotlinx.coroutines.CoroutineScope

class ClockSegmentState(val type: SegmentType) {
    var currentValue by mutableStateOf(0)
        private set
    var littleClockStates = mutableStateListOf<LittleClockElementState>()
        private set

    fun setNewValue(value: Int) {
        currentValue = value
        littleClockStates.clear()
        littleClockStates.addAll(getLittleClockStates(SegmentType.Number(currentValue)))
    }

    fun increment(maxValue: Int = 9): Boolean {
        if(type == SegmentType.Dots){
            return true
        }
        if (currentValue == maxValue) {
            setNewValue(0)
            return true
        }
        setNewValue(currentValue + 1)
        return false
    }

    init {
        littleClockStates.clear()
        littleClockStates.addAll(getLittleClockStates(type))
        if(type is SegmentType.Number){
            currentValue = type.value
        }
    }
}

@Composable
fun rememberClockSegmentState(
    type: SegmentType = SegmentType.Number(0)
): ClockSegmentState = remember { ClockSegmentState(type) }

@Composable
fun ClockSegment(modifier: Modifier, state: ClockSegmentState) {
    BoxWithConstraints(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        val maxArrowSizeToFitWidth = maxWidth / 8
        val maxArrowSizeToFitHeight = maxHeight / 12
        val arrowSize = min(maxArrowSizeToFitWidth, maxArrowSizeToFitHeight)
        Log.d(
            "SLVK_LOG",
            "ClockSegment. MaxWidth: $maxWidth, MaxHeight: $maxHeight, arrowSize: $arrowSize"
        )
        LazyVerticalGrid(
            modifier = Modifier.aspectRatio(2 / 3f),
            columns = GridCells.Fixed(4),
            contentPadding = PaddingValues(0.dp),

            ) {
            items(state.littleClockStates.size) {
                LittleClockElement(
                    modifier = Modifier.wrapContentSize(),
                    state = state.littleClockStates[it],
                    arrowLength = arrowSize,
                )
            }
        }
    }
}

fun min(value1: Dp, value2: Dp): Dp {
    return if (value1 > value2) return value2 else value1
}
