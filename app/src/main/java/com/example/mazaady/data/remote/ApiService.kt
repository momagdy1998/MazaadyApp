package com.example.mazaady.data.remote

import com.example.mazaady.data.model.allCategoriesResponse.AllCategoriesResponse
import com.example.mazaady.data.model.allPropertiesResponse.AllPropertiesResponse
import com.example.mazaady.data.model.getAllOptions.GetOptionsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("get_all_cats")
    suspend fun getAllCategories(
        @Header("private-key") privateKey: String
    ): Response<AllCategoriesResponse>

    @GET("properties")
    suspend fun getAllProperties(
        @Query("cat") subCategoriesId: Int,
        @Header("private-key") privateKey: String
    ): Response<AllPropertiesResponse>


    @GET("get-options-child/{option_id}")
    suspend fun getChildOptions(
        @Path("option_id") optionId: Int,
        @Header("private-key") privateKey: String
    ): Response<GetOptionsResponse>

}
