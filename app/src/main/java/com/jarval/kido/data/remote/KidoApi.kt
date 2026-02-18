package com.jarval.kido.data.remote

import com.jarval.kido.data.remote.model.Category
import retrofit2.http.GET

interface KidoApi {

    @GET("/api/categories")
    suspend fun getCategories(): List<Category>

}