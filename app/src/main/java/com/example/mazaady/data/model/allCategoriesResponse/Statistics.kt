package com.example.mazaady.data.model.allCategoriesResponse


import com.google.gson.annotations.SerializedName

data class Statistics(
    @SerializedName("auctions")
    val auctions: Int?,
    @SerializedName("products")
    val products: Int?,
    @SerializedName("users")
    val users: Int?
)