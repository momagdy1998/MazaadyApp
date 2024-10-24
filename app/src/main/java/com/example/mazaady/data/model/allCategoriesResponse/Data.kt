package com.example.mazaady.data.model.allCategoriesResponse


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("ads_banners")
    val adsBanners: List<AdsBanner?>?,
    @SerializedName("categories")
    val categories: List<Category?>?,
    @SerializedName("google_version")
    val googleVersion: String?,
    @SerializedName("huawei_version")
    val huaweiVersion: String?,
    @SerializedName("ios_latest_version")
    val iosLatestVersion: String?,
    @SerializedName("ios_version")
    val iosVersion: String?,
    @SerializedName("statistics")
    val statistics: Statistics?
)