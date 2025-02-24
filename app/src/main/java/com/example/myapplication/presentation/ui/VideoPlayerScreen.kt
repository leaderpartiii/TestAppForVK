package com.example.myapplication.presentation.ui

import android.widget.FrameLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentActivity
import com.example.myapplication.R
import com.example.myapplication.data.model.Video


@Composable
fun VideoPlayerScreen(video: Video, onBackPress: () -> Unit) {
    val context = LocalContext.current
    val activity = context as? FragmentActivity
        ?: throw IllegalStateException("Activity должна быть FragmentActivity")

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { ctx ->
            FrameLayout(ctx).apply {
                id = R.id.fragment_container
            }
        }
    )

    LaunchedEffect(video.videoUrl) {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, VideoPlayerFragment.newInstance(video.videoUrl))
            .commit()
    }

    BackButton(onBackPress)
}


@Composable
fun BackButton(onBackPress: () -> Unit) {
    IconButton(
        onClick = onBackPress,
        modifier = Modifier
            .padding(16.dp)
    ) {
        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
    }
}
