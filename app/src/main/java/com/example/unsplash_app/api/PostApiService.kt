package com.example.unsplash_app.api

import com.example.unsplash_app.model.PhotoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApiService {

    @GET("/photos")
    suspend fun getAllPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ) : List<PhotoResponse>

}