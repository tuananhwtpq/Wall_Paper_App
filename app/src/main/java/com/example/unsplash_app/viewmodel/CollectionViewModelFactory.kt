package com.example.unsplash_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.unsplash_app.repository.CollectionRepository

class CollectionViewModelFactory(private val repo: CollectionRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CollectionViewModel::class.java)){
            return CollectionViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}