package com.example.unsplash_app.model

import com.google.gson.annotations.SerializedName

data class Tag(
    @SerializedName("title")
    val title: String?,
)
