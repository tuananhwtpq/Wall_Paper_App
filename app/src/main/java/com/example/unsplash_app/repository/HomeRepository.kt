package com.example.unsplash_app.repository

import androidx.annotation.experimental.Experimental
import com.example.unsplash_app.api.PostApiService
import com.example.unsplash_app.model.PhotoResponse

class HomeRepository(private val apiService: PostApiService) {

    suspend fun getAllPhotos(page: Int, perPage: Int): Result<List<PhotoResponse>> {
        return try {
            val photos = apiService.getAllPhotos(page, perPage)
            Result.success(photos)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}