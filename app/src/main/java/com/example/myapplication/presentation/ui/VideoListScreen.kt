package com.example.myapplication.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.*
import androidx.compose.runtime.*
import com.example.myapplication.data.model.Video
import com.example.myapplication.presentation.viewmodel.VideoListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoListScreen(
    viewModel: VideoListViewModel,
    onVideoClick: (Video) -> Unit
) {
    val videos by viewModel.videos.collectAsState()
    val refreshing by viewModel.isRefreshing.collectAsState()

    val pullToRefreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        isRefreshing = refreshing,
        onRefresh = { viewModel.loadVideos() },
        state = pullToRefreshState
    ) {
        LazyColumn {
            items(videos) { video ->
                VideoItemScreen(video, onVideoClick)
            }
        }
    }
}
