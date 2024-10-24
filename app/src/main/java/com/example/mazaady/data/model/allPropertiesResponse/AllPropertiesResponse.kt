package com.example.mazaady.data.model.allPropertiesResponse


import com.google.gson.annotations.SerializedName

data class AllPropertiesResponse(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("data")
    val `data`: List<Data?>?,
    @SerializedName("msg")
    val msg: String?
)