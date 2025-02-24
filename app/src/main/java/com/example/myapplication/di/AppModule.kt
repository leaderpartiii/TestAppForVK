package com.example.myapplication.di

import com.example.myapplication.data.repository.VideoRepository
import com.example.myapplication.data.network.VideoApiService
import com.example.myapplication.presentation.viewmodel.VideoListViewModel
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideVideoApiService(): VideoApiService {
        return VideoApiService()
    }

    @Provides
    fun provideVideoRepository(apiService: VideoApiService): VideoRepository {
        return VideoRepository(apiService)
    }

    @Provides
    fun provideVideoListViewModel(repository: VideoRepository): VideoListViewModel {
        return VideoListViewModel(repository)
    }
}
