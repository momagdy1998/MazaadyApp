package com.example.mazaady.data.model.allCategoriesResponse


import com.google.gson.annotations.SerializedName

data class AdsBanner(
    @SerializedName("duration")
    val duration: Int?,
    @SerializedName("img")
    val img: String?,
    @SerializedName("media_type")
    val mediaType: String?
)