package com.example.unsplash_app.repository

import android.provider.ContactsContract
import com.example.unsplash_app.api.PostApiService
import com.example.unsplash_app.model.PhotoResponse
import com.example.unsplash_app.model.User

class HomeItemDetailRepository(private val apiService: PostApiService) {

    suspend fun getPrivateProfile(username: String): Result<User> {
        return try {
            val result = apiService.getPublicProfile(username)
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getPhotoById(id: String) : Result<PhotoResponse>{
        return try {

            val result = apiService.getPhotoById(id)
            Result.success(result)

        } catch (e: Exception){
            Result.failure(e)
        }
    }
}