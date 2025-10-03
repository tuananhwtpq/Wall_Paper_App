package com.example.unsplash_app.repository

import com.example.unsplash_app.api.PostApiService
import com.example.unsplash_app.model.CollectionResponse

class CollectionRepository(private val apiService: PostApiService) {

    suspend fun getAllCollections(page: Int, perPage: Int): Result<List<CollectionResponse>> {

        return try {
            val collections = apiService.getAllCollections(page, perPage)
            Result.success(collections)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getPrivateCollections(id: String) : Result<List<CollectionResponse>>{
        return try {
            val collections = apiService.getPrivateCollections(id)
            Result.success(collections)
        } catch (e: Exception){
            Result.failure(e)
        }
    }

}