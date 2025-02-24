package com.example.myapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.Video
import com.example.myapplication.data.repository.VideoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VideoListViewModel(
    private val repository: VideoRepository
) : ViewModel() {

    private val _videos = MutableStateFlow<List<Video>>(emptyList())
    val videos: StateFlow<List<Video>> = _videos

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    init {
        loadVideos()
    }

    fun loadVideos() {
        viewModelScope.launch {
            _isRefreshing.value = true
            _videos.value = repository.getVideos()
            _isRefreshing.value = false
        }
    }

    fun getVideoById(videoUrl: String?): Video? {
        return _videos.value.find { it.videoUrl == videoUrl }
    }
}
