package com.compose.core

sealed class ProgressBarState {

    object Loading : ProgressBarState()

    object Idle : ProgressBarState()
}
