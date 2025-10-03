package com.example.unsplash_app.api

import com.example.unsplash_app.model.CollectionResponse
import com.example.unsplash_app.model.PhotoResponse
import com.example.unsplash_app.model.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostApiService {

    @GET("/photos")
    suspend fun getAllPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ) : List<PhotoResponse>

    @GET("/collections")
    suspend fun getAllCollections(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<CollectionResponse>

    @GET("/collections/{id}")
    suspend fun getPrivateCollections(
        @Query("id") id: String
    ): List<CollectionResponse>

    @GET("users/{username}")
    suspend fun getPublicProfile(
        @Path("username") username: String
    ) : User

    @GET("photos/{id}")
    suspend fun getPhotoById(
        @Query("id") id: String
    ) : PhotoResponse

}