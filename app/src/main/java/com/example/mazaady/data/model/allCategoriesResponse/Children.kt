package com.example.mazaady.data.model.allCategoriesResponse


import com.google.gson.annotations.SerializedName

data class Children(
    @SerializedName("children")
    val children: Any?,
    @SerializedName("circle_icon")
    val circleIcon: String?,
    @SerializedName("description")
    val description: Any?,
    @SerializedName("disable_shipping")
    val disableShipping: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("slug")
    val slug: String?
)