package com.example.mazaady.data.model.allPropertiesResponse


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("list")
    val list: Boolean?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("options")
    val options: List<Option?>?,
    @SerializedName("other_value")
    val otherValue: Any?,
    @SerializedName("parent")
    val parent: Any?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("value")
    val value: String?
)