package com.example.myapplication.data.network

import com.example.myapplication.data.model.Video
import kotlinx.serialization.Serializable

@Serializable
data class YoutubeResponse(
    val items: List<Video>
)
