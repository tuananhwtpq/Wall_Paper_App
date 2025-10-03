package com.example.unsplash_app.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class CoverPhoto (
    @SerializedName("id")
    val id: String,

    @SerializedName("urls")
    val urls: PhotoUrl?
)