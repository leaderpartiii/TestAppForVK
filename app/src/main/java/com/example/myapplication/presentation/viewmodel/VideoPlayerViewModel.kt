package com.example.myapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class VideoPlayerViewModel @Inject constructor() : ViewModel() {
    private val _isPlaying = MutableStateFlow(true)
    val isPlaying: StateFlow<Boolean> = _isPlaying

    fun togglePlayPause() {
        _isPlaying.value = !_isPlaying.value
    }
}
