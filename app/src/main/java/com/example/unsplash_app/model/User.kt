package com.example.unsplash_app.model

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("name")
    val name: String?,

    @SerializedName("username")
    val username: String?,

    @SerializedName("profile_image")
    val profile_image: ProfileImage?
)