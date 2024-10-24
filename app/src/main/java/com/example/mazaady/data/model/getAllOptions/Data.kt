package com.example.mazaady.data.model.getAllOptions


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("description")
    val description: Any?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("list")
    val list: Boolean?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("options")
    val options: List<Any?>?,
    @SerializedName("other_value")
    val otherValue: Any?,
    @SerializedName("parent")
    val parent: Int?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("type")
    val type: Any?,
    @SerializedName("value")
    val value: String?
)