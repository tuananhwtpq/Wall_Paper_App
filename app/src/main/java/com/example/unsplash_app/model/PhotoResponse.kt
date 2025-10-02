package com.example.unsplash_app.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName

data class PhotoResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("urls")
    val urls: PhotoUrl?,

    @SerializedName("user")
    val user: User?
)