package com.example.myapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

@Serializable
@Entity(tableName = "Video")
data class Video(
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val videoUrl: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
) {
    constructor(special: Boolean) : this(
        title = "Rick Astley - Never Gonna Give You Up (Official Music Video)",
        thumbnailUrl = "https://i.ytimg.com/vi/dQw4w9WgXcQ/hqdefault.jpg",
        videoUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ",
        description = "The official video for “Never Gonna Give You Up” by Rick Astley. Never: The Autobiography OUT NOW! Follow this link to get ..."
    )
}



