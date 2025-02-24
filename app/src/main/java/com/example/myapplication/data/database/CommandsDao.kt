package com.example.myapplication.data.database


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.data.model.Video

@Dao
interface CommandsDao {
    @Insert
    fun insertVideo(person: Video)

    @Query("SELECT * FROM video")
    fun getAllVideos(): List<Video>

    @Query("DELETE FROM video")
    fun clearVideos(): Int
}