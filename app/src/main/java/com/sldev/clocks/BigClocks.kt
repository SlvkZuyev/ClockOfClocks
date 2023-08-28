package com.sldev.clocks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sldev.clocks.clock_numbers_states.SegmentType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BigClocksState(private val scope: CoroutineScope) {
    val segments: List<ClockSegmentState> = mutableListOf(
        ClockSegmentState(SegmentType.Number(5)),
        ClockSegmentState(SegmentType.Number(9)),
        ClockSegmentState(SegmentType.Dots),
        ClockSegmentState(SegmentType.Number(5)),
        ClockSegmentState(SegmentType.Number(9)),
        ClockSegmentState(SegmentType.Dots),
        ClockSegmentState(SegmentType.Number(4)),
        ClockSegmentState(SegmentType.Number(8)),
    )
    var isRunning = false
    val ticksDelay = 1000L

    init {
        start()
    }

    fun start() {
        if (isRunning) return
        isRunning = true
        scope.launch {
            while (isRunning) {
                nextTick()
                delay(ticksDelay)
            }
        }
    }

    fun pause() {
        isRunning = false
    }

    private fun nextTick() {
        incrementAll(segments.lastIndex)
    }

    private fun incrementAll(index: Int) {
        if (index < 0) return
        val maxValue = if (index % 3 == 0) 5 else 9
        if (segments[index].increment(maxValue)) {
            incrementAll(index - 1)
        }
    }

}


@Composable
fun rememberBigClockState(scope: CoroutineScope = rememberCoroutineScope()) = remember {
    BigClocksState(scope)
}

@Composable
fun BigClocks(modifier: Modifier, state: BigClocksState) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        state.segments.onEach {
            ClockSegment(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp), state = it
            )
        }
    }
}