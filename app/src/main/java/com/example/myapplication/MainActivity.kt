package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.presentation.ui.VideoListScreen
import com.example.myapplication.presentation.ui.VideoPlayerScreen
import com.example.myapplication.presentation.viewmodel.VideoListViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var videoListViewModel: VideoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen(videoListViewModel)
        }
    }
}

@Composable
fun MainScreen(viewModel: VideoListViewModel) {
    val navController = rememberNavController()
    VideoAppNavHost(navController, viewModel)
}

@Composable
fun VideoAppNavHost(
    navController: NavHostController,
    viewModel: VideoListViewModel
) {
    NavHost(navController, startDestination = "video_list") {
        composable("video_list") {
            VideoListScreen(viewModel) { video ->
                navController.navigate("video_player/${video.videoUrl}")
            }
        }
        composable("video_player/{videoUrl}") { backStackEntry ->
            val videoUrl = backStackEntry.arguments?.getString("videoUrl")
            val video = viewModel.getVideoById(videoUrl)

            video?.let {
                VideoPlayerScreen(it) { navController.popBackStack() }
            }
        }
    }
}
