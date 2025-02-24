package com.example.myapplication.data.network

import android.util.Log
import com.example.myapplication.BuildConfig
import com.example.myapplication.data.model.Video
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class VideoApiService(
    private val client: HttpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }
) {
    private val apiKey = BuildConfig.YOUTUBE_API_KEY
    private val countsOfVideos = 3
    private val nameVideo = "cats"
    private val url =
        "https://www.googleapis.com/youtube/v3/search?key=$apiKey&maxResults=$countsOfVideos&part=snippet&type=video&q=$nameVideo"

    suspend fun getVideos(): List<Video> {
        val response: String = client.get(url).body()
        if (response.contains("error")) {
            Log.d("Video api service", url + response)
            throw Exception("Error API: $response")
        }
        Log.d("Video api service", response)
        try {
            val jsonElement = Json.parseToJsonElement(response)
            val items = jsonElement.jsonObject["items"]?.jsonArray ?: return emptyList()
            return items.mapNotNull { item ->
                try {
                    val snippet = item.jsonObject["snippet"]?.jsonObject ?: return@mapNotNull null
                    val id =
                        item.jsonObject["id"]?.jsonObject?.get("videoId")?.jsonPrimitive?.content
                            ?: return@mapNotNull null
                    val title = snippet["title"]?.jsonPrimitive?.content ?: return@mapNotNull null
                    val description = snippet["description"]?.jsonPrimitive?.content ?: ""
                    val thumbnailUrl = snippet["thumbnails"]?.jsonObject
                        ?.get("medium")?.jsonObject
                        ?.get("url")?.jsonPrimitive?.content ?: return@mapNotNull null

                    Video(
                        title = title,
                        thumbnailUrl = thumbnailUrl,
                        videoUrl = "https://www.youtube.com/watch?v=$id",
                        description = description
                    )
                } catch (e: Exception) {
                    Log.e("VideoApiService", "Some errors with parsing ${e.message}")
                    null
                }
            }
        } catch (e: Exception) {
            Log.e("VideoApiService", "Ошибка обработки JSON: ${e.message}")
            return emptyList()
        }
    }
}

