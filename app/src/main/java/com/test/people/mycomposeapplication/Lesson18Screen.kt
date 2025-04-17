package com.test.people.mycomposeapplication

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay

private const val TAG = "Lesson18Screen"

@Composable
fun HomeScreen18() {
    Column (modifier = Modifier.padding(top = 80.dp, start = 16.dp, end = 16.dp)) {
        var sliderPosition by remember { mutableStateOf(1f) }
        Slider(
            value = sliderPosition,
            valueRange = 0f..10f,
            onValueChange = { sliderPosition = it })

        TrackPosition(position = sliderPosition)
    }
}

@Composable
fun TrackPosition(position: Float) {
    val positionState = remember {
        mutableStateOf(position)
    }
    positionState.value = position
    LaunchedEffect(key1 = Unit) {
        while(true) {
            delay(1000)
            Log.d(TAG, "track position ${positionState.value}")
        }
    }
}