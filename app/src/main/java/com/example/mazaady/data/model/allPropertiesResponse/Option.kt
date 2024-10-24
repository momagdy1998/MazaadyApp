package com.example.mazaady.data.model.allPropertiesResponse


import com.google.gson.annotations.SerializedName

data class Option(
    @SerializedName("child")
    val child: Boolean?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("parent")
    val parent: Int?,
    @SerializedName("slug")
    val slug: String?
)