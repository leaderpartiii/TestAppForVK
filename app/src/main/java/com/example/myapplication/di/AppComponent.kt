package com.example.myapplication.di

import com.example.myapplication.MainActivity
import com.example.myapplication.presentation.viewmodel.VideoListViewModel
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}
