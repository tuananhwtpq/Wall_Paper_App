package com.example.unsplash_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.unsplash_app.repository.HomeItemDetailRepository

class HomeItemDetailViewModelFactory(private val repo: HomeItemDetailRepository): ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeItemDetailViewModel::class.java)){
            return HomeItemDetailViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}