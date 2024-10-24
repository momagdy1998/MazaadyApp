package com.example.mazaady.data.model.getAllOptions


import com.google.gson.annotations.SerializedName

data class GetOptionsResponse(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("data")
    val `data`: List<Data?>?,
    @SerializedName("msg")
    val msg: String?
)