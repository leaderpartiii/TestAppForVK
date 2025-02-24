package com.example.myapplication.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

@Serializable
data class Video(
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val videoUrl: String,
)

