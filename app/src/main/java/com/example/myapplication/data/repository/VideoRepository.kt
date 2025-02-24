package com.example.myapplication.data.repository

import com.example.myapplication.data.model.Video
import com.example.myapplication.data.network.VideoApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class VideoRepository(private val api: VideoApiService) {
    suspend fun getVideos(): List<Video> = api.getVideos()
}

