package com.example.mazaady.data.model.allCategoriesResponse


import com.google.gson.annotations.SerializedName

data class AllCategoriesResponse(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("msg")
    val msg: String?
)